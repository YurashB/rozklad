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
            v-model="student.name"
            :rules="nameRules"
            label="Name of teacher"
            type="text"
            counter="20"
        >
        </v-text-field>
        <v-text-field
            class="text-field-m-p"
            variant="outlined"
            v-model="student.email"
            :rules="emailRules"
            label="Email of teacher"
            type="email"
            counter="128"
        >
        </v-text-field>
        <v-text-field
            class="text-field-m-p"
            variant="outlined"
            v-model="student.phone"
            v-mask="'38(0##)###-##-##'"
            placeholder="38(0__)___-__-__"
            :rules="phoneRules"
            label="Phone of teacher"
            type="tel"
            counter="16"
        >
        </v-text-field>
        <v-autocomplete
            label="Group"
            v-model="student.group_id"
            :items="groups"
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
import {mask} from 'vue-the-mask'
import axios from "axios";

export default {
  data() {
    return {
      valid: false,
      headerTitle: this.$route.params.id ? "Change student" : "Add new student",
      nameRules: [v => !!v || 'Name is required',   v => v.length <= 20 || 'Max characters entered'],
      emailRules: [
        v => !!v || 'Email is required',
        v => v.length <= 128 || 'Max characters entered',
        v => /^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/.test(v) || " Invalid email entered"
      ],
      phoneRules: [
        v => !!v || 'Phone is required',
        v => v.length > 15 || "Invalid phone entered"
      ],
      selectRules: [
        v => !!v || 'Group is required',
      ],
      student: {
        id: this.$route.params.id,
        name: "",
        email: "",
        phone: "",
        group_id: ""
      },
      groups: []
    }
  },
  directives: {
    mask
  },
  methods: {
    change() {
      const routeId = this.$route.params.id;

      const params = new URLSearchParams();
      params.append('name', this.student.name);
      params.append('email', this.student.email);
      params.append('phone', this.student.phone);
      params.append('group_id', this.student.group_id);

      if (routeId) {
        axios.put("/api/students/" + routeId, params)
            .then(response => {
              response.status === 200 ? this.$router.push({name: "successPage"}) : console.log(response);
            }).catch(e => {
          e.request.status === 500 ? this.$router.push({name: "serverErrorPage"}) : console.log(e);
        })
      }
    },
    add(event) {
      event.preventDefault();
      axios.post("/api/students", {
        name: this.student.name,
        email: this.student.email,
        phone: this.student.phone,
        group_id: this.student.group_id
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
      axios.get("/api/students/" + routeId, {
        params: {
          id: routeId
        }
      })
          .then(response => {
            console.log(response)
            this.student.name = response.data.name;
            this.student.email = response.data.email;
            this.student.phone = response.data.phone;
            this.student.group_id = response.data.group.id;
          })
          .catch(e => {
            e.request.status === 404 ? this.$router.push({name: "notFound"}) : "";
            e.request.status === 500 ? this.$router.push({name: "serverErrorPage"}) : console.log(e);
          });

    }

    axios.get("/api/groups")
        .then(response => {
          this.groups = response.data;
        })
        .catch(e => {
          console.log(e)
          this.$router.push({name: "serverErrorPage"})
        })
  }
}
</script>
