<template>
  <v-card elevation="1" width="40%" class="mx-auto m-p ">
    <v-card-title>
      <h2>{{ headerTitle }}</h2>
    </v-card-title>
    <v-card-text>
      <v-form v-model="valid" lazy-validation ref="form">
        <v-text-field
            class="text-field-m-p"
            variant="outlined"
            v-model="faculty.name"
            :rules="nameRules"
            label="Name of faculty"
            type="text"
        >
        </v-text-field>
        <v-text-field
            class="text-field-m-p"
            variant="outlined"
            v-model="faculty.shortName"
            :rules="nameRules"
            label="Short name of faculty"
            type="text"
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
            @click="add($event)"
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

      faculty: {
        id: this.$route.params.id,
        name: "",
        shortName: "",
      },
      valid: false,
      nameRules: [
        v => !!v || 'Name is required',
      ],
      headerTitle: this.$route.params.id ? "Change faculty" : "Add new faculty",
    }
  },
  methods: {
    change() {
      const routeId = this.$route.params.id;

      const params = new URLSearchParams();
      params.append('name', this.faculty.name);
      params.append('shortName', this.faculty.shortName);

      if (routeId) {
        axios.put("/api/faculties/" + routeId, params)
            .then(response => {
              response.status === 200 ? this.$router.push({name: "successPage"}) : console.log(response);
            }).catch(e => {
          e.request.status === 500 ? this.$router.push({name: "serverErrorPage"}) : console.log(e);
        })
      }
    },
    add(event) {
      event.preventDefault();
      axios.post("/api/faculties", {
        name: this.faculty.name,
        shortName: this.faculty.shortName,
      })
          .then(response => {
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
      axios.get("/api/faculties/" + routeId, {
        params: {
          id: routeId
        }
      })
          .then(response => {
            console.log(response)
            this.faculty.name = response.data.name;
            this.faculty.shortName = response.data.shortName;
          })
          .catch(e => {
            e.request.status === 404 ? this.$router.push({name: "notFound"}) : "";
            e.request.status === 500 ? this.$router.push({name: "serverErrorPage"}) : console.log(e);

          })
    }
  }
}
</script>
