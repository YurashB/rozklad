<template>
  <v-card elevation="0">
    <v-card-title>
      <h1>Schedules</h1>
    </v-card-title>
    <v-card-text>
      <vue-good-table
          ref="table"
          :columns="columns"
          :rows="rows"
          :select-options="{ enabled: true }"
          :search-options="{ enabled: true }"
      >
        <template #selected-row-actions>
          <v-btn
              v-show="this.$refs['table'].selectedRows.length === 1"
              class="font"
              size="x-small"
              color="green"
              variant="plain"
              @click="changeItem"

          >Change
          </v-btn>
          <v-btn
              class="font"
              size="x-small"
              color="red"
              variant="plain"
              @click="deleteItem"
          >Delete
          </v-btn>
        </template>
      </vue-good-table>
    </v-card-text>
  </v-card>
</template>

<script>
export default {
  name: "ScheduleTable",
  data() {
    return {
      columns: [
        {
          label: 'Teacher',
          field: 'teacher',
        },
        {
          label: 'Discipline',
          field: 'discipline',
        },
        {
          label: 'Group',
          field: 'group',
        },
        {
          label: 'Time',
          field: 'time',
        },
        {
          label: 'Classroom',
          field: 'classroom',
        },
      ],
      rows: [
        {
          id: 1,
          name: "schedule name",
          teacher: "Yurash Bohdan",
          teacher_id: "1",
          discipline: "OS",
          discipline_id: "1",
          group: "IA-11",
          group_id: "1",
          time: "12:20 - 14:05",
          classroom: "256"
        },
        {
          id: 1,
          name: "schedule name",
          teacher: "Yurash Bohdan",
          discipline: "OS",
          group: "IA-11",
          time: "12:20 - 14:05",
          classroom: "256",
          teacher_id: "1",
          discipline_id: "1",
          group_id: "1",
        },
        {
          id: 1,
          name: "schedule name",
          teacher: "Yurash Bohdan",
          discipline: "OS",
          group: "IA-11",
          time: "12:20 - 14:05",
          classroom: "256",
          teacher_id: "1",
          discipline_id: "1",
          group_id: "1",
        },
        {
          id: 1,
          name: "schedule name",
          teacher: "Yurash Bohdan",
          discipline: "OS",
          group: "IA-11",
          time: "12:20 - 14:05",
          classroom: "256",
          teacher_id: "1",
          discipline_id: "1",
          group_id: "1",
        },
        {
          id: 1,
          name: "schedule name",
          teacher: "Yurash Bohdan",
          discipline: "OS",
          group: "IA-11",
          time: "12:20 - 14:05",
          classroom: "256",
          teacher_id: "1",
          discipline_id: "1",
          group_id: "1",
        }
      ],
    }
  },
  methods: {
    deleteItem() {

    },
    changeItem() {
      let selected = this.$refs['table'].selectedRows[0];
      let id = selected.id;
      let teacher_id = selected.teacher_id;
      let discipline_id = selected.discipline_id;
      let group_id = selected.group_id;
      let time = selected.time;
      let classroom = selected.classroom;


      this.$router.push({
        name: "updateSchedule",
        params: {id: id},
        query: {
          id: id,
          teacher_id: teacher_id,
          discipline_id: discipline_id,
          group_id,
          time: time,
          classroom: classroom
        },
      })
    },
  }
}
</script>

<style scoped>

</style>