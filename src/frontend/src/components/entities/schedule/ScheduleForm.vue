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
            v-model="this.schedule.time"
            :rules="timeRules"
            hint="Format: 12:20 - 14:05"
            label="Time of schedule"
            type="text"
        >
        </v-text-field>
        <v-text-field
            class="text-field-m-p"
            variant="outlined"
            v-model="this.schedule.classroom"
            :rules="nameRules"
            label="Classroom of schedule"
            type="text"
        >
        </v-text-field>
        <v-autocomplete
            label="Teacher"
            v-model="this.schedule.teacher_id"
            :items="teachers"
            item-title="name"
            item-value="id"
            variant="solo"
            :rules="selectRules"
        ></v-autocomplete>
        <v-autocomplete
            label="Group"
            v-model="this.schedule.group_id"
            :items="groups"
            item-title="name"
            item-value="id"
            variant="solo"
            :rules="selectRules"
        ></v-autocomplete>
        <v-autocomplete
            label="Discipline"
            v-model="this.schedule.discipline_id"
            :items="disciplines"
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
      headerTitle: this.$route.query.id ? "Change schedule" : "Add new schedule",
      nameRules: [v => !!v || 'Name is required',
        v => /^[А-ЩЬЮЯҐЄІЇа-щьюяґєії ]+$/.test(v) || "Only letters are required"],
      timeRules: [
        v => !!v || 'Time is required',
        v => this.isTimeHaveCorrectFormat(v) || "Invalid time entered"
      ],
      emailRules: [
        v => !!v || 'Email is required',
        v => /^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/.test(v) || " Invalid email entered"
      ],
      selectRules: [
        v => !!v || 'Is required',
      ],
      schedule: {
        id: this.$route.query.id,
        teacher_id: this.$route.query.teacher_id,
        discipline_id: this.$route.query.discipline_id,
        group_id: this.$route.query.group_id,
        time: this.$route.query.time,
        classroom: this.$route.query.classroom
      },
      groups: [
        {id: "1", name: "IA-11"},
        {id: "2", name: "IA-12"},
        {id: "3", name: "IК-11"},
        {id: "4", name: "IC-14"}
      ],
      disciplines: [
        {id: "1", name: "English"},
        {id: "2", name: "Web development"},
        {id: "3", name: "OS"},
        {id: "4", name: "Front-end"},
        {id: "5", name: "Math"},
      ],
      teachers: [
        {id: "1", name: "Gisselle Turner"},
        {id: "2", name: "Dereck Wehner"},
        {id: "3", name: "Rollin Johnson"},
        {id: "4", name: "Gisselle Kunze"},
        {id: "5", name: "Toy Friesen"},
      ]
    }
  },
  methods: {
    isTimeHaveCorrectFormat(time) {
      //12:20 - 14:05
      const timeArray = Array.from(time);
      return !(timeArray.length === 13 &&
          Number.isInteger(timeArray[0]) &&
          Number.isInteger(timeArray[1]) &&
          Number.isInteger(timeArray[4]) &&
          Number.isInteger(timeArray[5]) &&
          Number.isInteger(timeArray[8]) &&
          Number.isInteger(timeArray[9]) &&
          Number.isInteger(timeArray[11]) &&
          Number.isInteger(timeArray[12]));
    },
    change() {

    },
    add() {

    }
  }
}
</script>
