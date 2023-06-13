<template>
  <v-card elevation="1" width="40%" class="mx-auto m-p ">
    <v-card-title>
      <h2>{{ headerTitle }}</h2>
    </v-card-title>
    <v-card-text>
      <v-form v-model="valid" lazy-validation ref="form" pre>
        <v-text-field
            class="text-field-m-p"
            variant="outlined"
            v-model="discipline.name"
            :rules="nameRules"
            label="Name of discipline"
            type="text"
            counter="64"
        >
        </v-text-field>
        <v-btn
            v-if="this.$route.params.id"
            width="100%"
            color="green"
            variant="elevated"
            :disabled=!valid
            @click="change"
        >Change
        </v-btn>
        <v-btn
            v-if="!this.$route.params.id"
            width="100%"
            type="submit"
            color="green"
            variant="elevated"
            :disabled=!valid
            @click="add"
        >Add
        </v-btn>
      </v-form>
    </v-card-text>
  </v-card>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      valid: false,
      nameRules: [
        v => !!v || 'Name is required',
        v => v.length <= 64 || 'Max characters entered'
      ],
      discipline: {
        id: this.$route.params.id,
        name: "",
      },
      headerTitle: this.$route.params.id ? "Change discipline" : "Add new discipline",
    }
  },
  methods: {
    change() {
      const routeId = this.$route.params.id;

      const params = new URLSearchParams([['name', this.discipline.name]]);

      if (routeId) {
        axios.put("/api/disciplines/" + routeId, params)
            .then(response => {
              response.status === 200 ? this.$router.push({name: "successPage"}) : console.log(response);
            }).catch(e => {
          e.request.status === 500 ? this.$router.push({name: "serverErrorPage"}) : console.log(e);
        })
      }
    },
    add() {
      axios.post("/api/disciplines", {
        name: this.discipline.name
      })
          .then(response => {
            console.log(response)
            response.status === 200 ? this.$router.push({name: "successPage"}) : console.log(response);
          }).catch(e => {
        console.log(e)
        e.request.status === 500 ? this.$router.push({name: "serverErrorPage"}) : console.log(e);
      })
    }
  },
  mounted() {
    const routeId = this.$route.params.id;

    if (routeId) {
      axios.get("/api/disciplines/" + routeId, {
        params: {
          id: routeId
        }
      })
          .then(response => {
            this.discipline.name = response.data.name;
          })
          .catch(e => {
            e.request.status === 404 ? this.$router.push({name: "notFound"}) : "";
            e.request.status === 500 ? this.$router.push({name: "serverErrorPage"}) : console.log(e);

          })
    }
  }
}
</script>
