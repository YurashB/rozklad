<template>
  <v-card elevation="1" width="40%" class="mx-auto m-p ">
    <v-card-title>
      <h2>Add new faculty</h2>
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
        <v-btn
            :disabled="!valid"
            width="100%"
            color="green"
            type="submit"
            variant="elevated"
        >Add
        </v-btn>
      </v-form>
    </v-card-text>
  </v-card>
</template>

<script>
export default {
  name: "AppBar",
  data() {
    return {
      department: {
        id: "",
        name: "",
        short_name: ""
      },
      valid: false,
      nameRules: [
        v => !!v || 'Name is required',
        v => /^[a-zA-Z]+$/.test(v) || "Only letters are required"
      ],
    }
  }
}
</script>

<style scoped>
.m-p {
  margin: 20px;
  padding: 10px;
}

.text-field-m-p {
  margin: 7px;
}
</style>