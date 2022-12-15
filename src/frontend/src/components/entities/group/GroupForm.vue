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
      headerTitle: this.$route.query.id ? "Change group" : "Add new group",
      nameRules: [
        v => !!v || 'Name is required',
      ],
      courseRoles: [
        v => !!v || 'Name is required',
        v => v < 12 || "Too large number for course"
      ],
      selectRules: [
        v => !!v || 'Group is required',
      ],
      group: {
        id: this.$route.query.id,
        name: this.$route.query.name,
        course: this.$route.query.course,
        department_id: this.$route.query.department_id
      },
      departments: [
        {id: "1", name: "IST"},
        {id: "2", name: "SOI"},
        {id: "3", name: "SLA"}
      ]
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
