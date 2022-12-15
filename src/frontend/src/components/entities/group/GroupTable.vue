<template>
  <v-card elevation="0">
    <v-card-title>
      <h1>Groups</h1>
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
  name: "GroupTable",
  data() {
    return {
      columns: [
        {
          label: 'Name',
          field: 'name',
        },
        {
          label: 'Department',
          field: 'department',
        },
        {
          label: 'Course',
          field: 'course',
          type: 'number',
        }
      ],
      rows: [
        {id: 1, name: "Gisselle", department: "IST", course: 1, department_id: 1},
        {id: 2, name: "Gisselle", department: "IST", course: 1, department_id: 2},
        {id: 3, name: "Gisselle", department: "IST", course: 1, department_id: 3},
        {id: 4, name: "Gisselle", department: "IST", course: 1, department_id: 1},
      ],
    }
  },
  methods: {
    deleteItem(){

    },
    changeItem() {
      let selected = this.$refs['table'].selectedRows[0];
      let id = selected.id;
      let name = selected.name;
      let course = selected.course;
      let department_id = selected.department_id;

      this.$router.push({
        name: "updateGroup",
        params: {id: id},
        query: {id: id, name: name, course: course, department_id: department_id},
      })
    }
  }
}
</script>

<style scoped>

</style>