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
            v-model="teacher.name"
            :rules="nameRules"
            label="Name of teacher"
            type="text"
        >
        </v-text-field>
        <v-text-field
            class="text-field-m-p"
            variant="outlined"
            v-model="teacher.surname"
            :rules="nameRules"
            label="Surname of teacher"
            type="text"
        >
        </v-text-field>
        <v-text-field
            class="text-field-m-p"
            variant="outlined"
            v-model="teacher.email"
            :rules="emailRules"
            label="Email of teacher"
            type="email"
        >
        </v-text-field>
        <v-text-field
            class="text-field-m-p"
            variant="outlined"
            v-model="teacher.phone"
            v-mask="'38(0##)###-##-##'"
            placeholder="38(0__)___-__-__"
            :rules="phoneRules"
            label="Phone of teacher"
            type="tel"
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
import {mask} from 'vue-the-mask'
import axios from "axios";

export default {
  data() {
    return {
      valid: false,
      headerTitle: this.$route.query.id ? "Change teacher" : "Add new teacher",
      nameRules: [v => !!v || 'Name is required',],
      emailRules: [
        v => !!v || 'Email is required',
        v => /^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/.test(v) || " Invalid email entered"
      ],
      phoneRules: [
        v => !!v || 'Phone is required',
        v => v.length > 15 || "Invalid phone entered"
      ],
      teacher: {
        id: this.$route.params.id,
        name: "",
        surname: "",
        email: "",
        phone: ""
      }
    }
  },
  directives: {
    mask
  },
  methods: {
    change() {
      const routeId = this.$route.params.id;

      const params = new URLSearchParams();
      params.append('name', this.teacher.name);
      params.append('surname', this.teacher.surname);
      params.append('email', this.teacher.email);
      params.append('phone', this.teacher.phone);

      if (routeId) {
        axios.put("/api/teachers/" + routeId, params)
            .then(response => {
              response.status === 200 ? this.$router.push({name: "successPage"}) : console.log(response);
            }).catch(e => {
          e.request.status === 500 ? this.$router.push({name: "serverErrorPage"}) : console.log(e);
        })
      }
    },
    add(event) {
      event.preventDefault();
      axios.post("/api/teachers", {
        name: this.teacher.name,
        surname: this.teacher.surname,
        email: this.teacher.email.toString(),
        phone: this.teacher.phone.toString(),
      })
          .then(response => {
            response.status === 200 ? this.$router.push({name: "successPage"}) : console.log(response);
          }).catch(e => {
        console.log(e)
        e.request.status === 500 ? this.$router.push({name: "serverErrorPage"}) : console.log(e);
      })
    },
  },
  mounted() {
    const routeId = this.$route.params.id;

    if (routeId) {
      axios.get("/api/teachers/" + routeId, {
        params: {
          id: routeId
        }
      })
          .then(response => {
            this.teacher.name = response.data.name;
            this.teacher.surname = response.data.surname;
            this.teacher.email = response.data.email;
            this.teacher.phone = response.data.phone;
          })
          .catch(e => {
            e.request.status === 404 ? this.$router.push({name: "notFound"}) : "";
            e.request.status === 500 ? this.$router.push({name: "serverErrorPage"}) : console.log(e);

          })
    }
  }
}
</script>
