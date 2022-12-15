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
        >
        </v-text-field>
        <v-text-field
            class="text-field-m-p"
            variant="outlined"
            v-model="student.email"
            :rules="emailRules"
            label="Email of teacher"
            type="email"
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
            @click="add"
        >Add
        </v-btn>
      </v-form>
    </v-card-text>
  </v-card>
</template>

<script>
import {mask} from 'vue-the-mask'

export default {
  data() {
    return {
      valid: false,
      headerTitle: this.$route.query.id ? "Change student" : "Add new student",
      nameRules: [v => !!v || 'Name is required',
        v => /^[А-ЩЬЮЯҐЄІЇа-щьюяґєії ]+$/.test(v) || "Only letters are required"],
      emailRules: [
        v => !!v || 'Email is required',
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
        id: this.$route.query.id,
        name: this.$route.query.name,
        email: this.$route.query.email,
        phone: this.$route.query.phone,
        group_id: this.$route.query.group_id
      },
      groups: [
        {id: "1", name: "IA-11"},
        {id: "2", name: "IA-12"},
        {id: "3", name: "IК-11"},
        {id: "4", name: "IC-14"}
      ]
    }
  },
  directives: {
    mask
  },
  methods: {
    change() {

    },
    add() {

    }
  }
}
</script>
