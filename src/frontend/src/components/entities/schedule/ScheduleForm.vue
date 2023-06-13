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
            counter="13"
        >
        </v-text-field>
        <v-text-field
            class="text-field-m-p"
            variant="outlined"
            v-model="this.schedule.classroom"
            :rules="classroomRules"
            label="Classroom of schedule"
            type="text"
            counter="10"
        >
        </v-text-field>
        <v-autocomplete
            label="Teacher"
            v-model="this.schedule.teacher_id"
            :items="teachers"
            :item-title="getTeacherTitle"
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
      headerTitle: this.$route.params.id ? "Change schedule" : "Add new schedule",
      classroomRules: [v => !!v || 'Name is required',   v => v.length <= 10 || 'Max characters entered'],
      timeRules: [
        v => !!v || 'Time is required',
        v => v.length <= 13 || 'Max characters entered',
        v => /[0-9 -:]+$/.test(v) || "Invalid time entered"
      ],
      selectRules: [
        v => !!v || 'Is required',
      ],
      schedule: {
        id: this.$route.params.id,
        teacher_id: "",
        discipline_id: "",
        group_id: "",
        time: "",
        classroom: ""
      },
      groups: [],
      disciplines: [],
      teachers: []
    }
  },
  methods: {
    getTeacherTitle(teacher){
      console.log(teacher)
      return teacher !== "" ? `${teacher.surname} ${teacher.name}`: ``
    },
    change() {
      const routeId = this.$route.params.id;

      const params = new URLSearchParams();
      params.append('time', this.schedule.time);
      params.append('classroom', this.schedule.classroom);
      params.append('teacher_id', this.schedule.teacher_id);
      params.append('group_id', this.schedule.group_id);
      params.append('discipline_id', this.schedule.discipline_id);

      if (routeId) {
        axios.put("/api/schedules/" + routeId, params)
            .then(response => {
              response.status === 200 ? this.$router.push({name: "successPage"}) : console.log(response);
            }).catch(e => {
          e.request.status === 500 ? this.$router.push({name: "serverErrorPage"}) : console.log(e);
        })
      }
    },
    add(event) {
      event.preventDefault();
      axios.post("/api/schedules", {
        time: this.schedule.time,
        classroom: this.schedule.classroom,
        teacher_id: this.schedule.teacher_id,
        group_id: this.schedule.group_id,
        discipline_id: this.schedule.discipline_id
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
      axios.get("/api/schedules/" + routeId, {
        params: {
          id: routeId
        }
      })
          .then(response => {
            console.log(response)
            this.schedule.time = response.data.time;
            this.schedule.classroom = response.data.classroom;
            this.schedule.teacher_id = response.data.teacher.id;
            this.schedule.group_id = response.data.group.id;
            this.schedule.discipline_id = response.data.discipline.id;
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

    axios.get("/api/teachers")
        .then(response => {
          this.teachers = response.data;
        })
        .catch(e => {
          console.log(e)
          this.$router.push({name: "serverErrorPage"})
        })

    axios.get("/api/disciplines")
        .then(response => {
          this.disciplines = response.data;
        })
        .catch(e => {
          console.log(e)
          this.$router.push({name: "serverErrorPage"})
        })
  }
}
</script>
