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
            :rules="nameRules"
            label="Short name of faculty"
            type="text"
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
            @click="add"
        >Add
        </v-btn>
      </v-form>
    </v-card-text>
  </v-card>
</template>

<script>
export default {
  data() {
    return {
      valid: false,
      nameRules: [
        v => !!v || 'Name is required',
        v => /^[А-ЩЬЮЯҐЄІЇа-щьюяґєії ]+$/.test(v) || "Only letters are required"
      ],
      selectRules: [
        v => !!v || 'Faculty is required',
      ],
      department: {
        id: this.$route.query.id,
        name: this.$route.query.name,
        short_name: this.$route.query.short_name,
        faculty_id: this.$route.query.faculty_id
      },
      faculties: [
        {id: "1", name: "FICT"},
        {id: "2", name: "FL"},
        {id: "3", name: "RTE"},
        {id: "4", name: "GLA"},
      ],
      headerTitle: this.$route.query.id ? "Change department" : "Add new department",
    }
  },
  methods: {
    change() {

    },
    add() {

    }
  }
}
</script>