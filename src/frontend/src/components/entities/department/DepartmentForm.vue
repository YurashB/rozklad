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
            counter="128"
            v-model="department.name"
            :rules="nameRules"
            label="Name of faculty"
            type="text"
        >
        </v-text-field>
        <v-text-field
            class="text-field-m-p"
            variant="outlined"
            v-model="department.short_name"
            :rules="shortNameRules"
            label="Short name of faculty"
            type="text"
            counter="10"
        >
        </v-text-field>
        <v-autocomplete
            label="Faculty"
            v-model="department.faculty_id"
            :items="faculties"
            item-title="name"
            item-value="id"
            variant="solo"
            :rules="selectRules"
        ></v-autocomplete>
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
      valid: false,
      nameRules: [
        v => !!v || 'Name is required',
        v => v.length <= 128 || 'Max characters entered'
      ],
      shortNameRules: [
        v => !!v || 'Name is required',
        v => v.length <= 10 || 'Max characters entered'
      ],
      selectRules: [
        v => !!v || 'Faculty is required',
      ],
      department: {
        id: this.$route.params.id,
        name: "",
        short_name: "",
        faculty_id: ""
      },
      faculties: [],
      headerTitle: this.$route.params.id ? "Change department" : "Add new department",
    }
  },
  methods: {
    change() {
      const routeId = this.$route.params.id;

      const params = new URLSearchParams();
      params.append('name', this.department.name);
      params.append('short_name', this.department.short_name);
      params.append('faculty_id', this.department.faculty_id);

      if (routeId) {
        axios.put("/api/departments/" + routeId, params)
            .then(response => {
              response.status === 200 ? this.$router.push({name: "successPage"}) : console.log(response);
            }).catch(e => {
          e.request.status === 500 ? this.$router.push({name: "serverErrorPage"}) : console.log(e);
        })
      }
    },
    add(event) {
      event.preventDefault();
      axios.post("/api/departments", {
        name: this.department.name,
        short_name: this.department.short_name,
        faculty_id: this.department.faculty_id
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
      axios.get("/api/departments/" + routeId, {
        params: {
          id: routeId
        }
      })
          .then(response => {
            console.log(response)
            this.department.name = response.data.name;
            this.department.short_name = response.data.shortName;
            this.department.faculty_id = response.data.faculty.id;
          })
          .catch(e => {
            e.request.status === 404 ? this.$router.push({name: "notFound"}) : "";
            e.request.status === 500 ? this.$router.push({name: "serverErrorPage"}) : console.log(e);
          });

    }

    axios.get("/api/faculties")
        .then(response => {
          this.faculties = response.data;
        })
        .catch(e => {
          console.log(e)
          this.$router.push({name: "serverErrorPage"})
        })
  }
}
</script>