<template>
  <v-card elevation="1" width="40%" class="mx-auto m-p">
    <v-card-title>
      <h2>{{ headerTitle }}</h2>
    </v-card-title>
    <v-card-text>
      <v-form v-model="valid" lazy-validation ref="form">
        <v-text-field
            class="text-field-m-p"
            variant="outlined"
            v-model="group.name"
            :rules="nameRules"
            label="Name of group"
            type="text"
            counter="8"
        >
        </v-text-field>
        <v-text-field
            class="text-field-m-p"
            variant="outlined"
            v-model="group.course"
            :rules="courseRoles"
            label="Course of group"
            type="number"
        >
        </v-text-field>
        <v-autocomplete
            label="Departments"
            v-model="group.department_id"
            :items="departments"
            item-title="shortName"
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
      headerTitle: this.$route.params.id ? "Change group" : "Add new group",
      nameRules: [
        v => !!v || 'Name is required',
        v => v.length <= 8 || 'Max characters entered'
      ],

      courseRoles: [
        v => !!v || 'Name is required',
        v => v < 12 || "Too large number for course"
      ],
      selectRules: [
        v => !!v || 'Group is required',
      ],
      group: {
        id: this.$route.params.id,
        name: "",
        course: "",
        department_id: ""
      },
      departments: []
    }
  },
  methods: {
    change() {
      const routeId = this.$route.params.id;

      const params = new URLSearchParams();
      params.append('name', this.group.name);
      params.append('course', this.group.course);
      params.append('department_id', this.group.department_id);

      if (routeId) {
        axios.put("/api/groups/" + routeId, params)
            .then(response => {
              response.status === 200 ? this.$router.push({name: "successPage"}) : console.log(response);
            }).catch(e => {
          e.request.status === 500 ? this.$router.push({name: "serverErrorPage"}) : console.log(e);
        })
      }
    },
    add(event) {
      event.preventDefault();
      axios.post("/api/groups", {
        name: this.group.name,
        course: this.group.course,
        department_id: this.group.department_id
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
      axios.get("/api/groups/" + routeId, {
        params: {
          id: routeId
        }
      })
          .then(response => {
            this.group.name = response.data.name;
            this.group.course = response.data.course;
            this.group.department_id = response.data.department.id;
          })
          .catch(e => {
            e.request.status === 404 ? this.$router.push({name: "notFound"}) : "";
            e.request.status === 500 ? this.$router.push({name: "serverErrorPage"}) : console.log(e);
          });

    }

    axios.get("/api/departments")
        .then(response => {
          this.departments = response.data;
        })
        .catch(e => {
          console.log(e)
          this.$router.push({name: "serverErrorPage"})
        })
  }
}
</script>
