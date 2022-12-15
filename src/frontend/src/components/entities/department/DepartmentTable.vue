<template>
  <v-card elevation="0">
    <v-card-title>
      <h2>Departments</h2>
    </v-card-title>
    <v-card-text>
      <vue-good-table
          :columns="columns"
          :rows="rows"
          :select-options="{ enabled: true }"
          :search-options="{ enabled: true }"
          ref="table"
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
  name: "DepartmentTable",
  data() {
    return {
      columns: [
        {
          label: 'Name',
          field: 'name',
        },
        {
          label: 'Short name',
          field: 'short_name',
        },
        {
          label: 'Faculty',
          field: 'faculty',
        },
      ],
      rows: [
        {id: 1, name: 'Department of Information Systems and Technologies', short_name: "IST", faculty: "FICT", faculty_id: 1},
        {id: 2, name: 'Technical System of Automation and Control of Production', short_name: "ASOIU", faculty: "FICT", faculty_id: 1},
        {id: 3, name: 'Department of computing technics', short_name: "OT", faculty: "FICT", faculty_id: 1},
        {id: 4, name: 'Department of Applied Radio Electronics', short_name: "DARE", faculty: "RTF", faculty_id: 1},
        {id: 5, name: 'Department of Radio Engineering', short_name: "DRE", faculty: "RTF", faculty_id: 1},
        {id: 6, name: 'Department of Theory, Practice and Translation of English', short_name: "DTPTE", faculty: "FL", faculty_id: 1},
        {id: 7, name: 'Department of Theory, Practice and Translation of French', short_name: "DTPTF", faculty: "FL", faculty_id: 1},
      ],
    }
  },
  methods: {
    deleteItem() {

    },
    changeItem() {
      let selected = this.$refs['table'].selectedRows[0];
      let id = selected.id;
      let name = selected.name;
      let short_name = selected.short_name;
      let faculty_id = selected.faculty_id;

      this.$router.push({
        name: "updateDepartment",
        params: {id: id},
        query: {id: id, name: name, short_name: short_name, faculty_id: faculty_id},
      })
    }
  }
}
</script>

<style scoped>

</style>