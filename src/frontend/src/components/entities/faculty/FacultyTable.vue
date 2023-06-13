<template>
  <v-card elevation="0">
    <v-card-title>
      <h1>Faculties</h1>
    </v-card-title>
    <v-card-text>
      <vue-good-table
          ref="table"
          :columns="columns"
          :rows="rows"
          :select-options="{ enabled: true }"
          :search-options="{ enabled: true }"
          :pagination-options="{ enabled: true }"
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
import axios from "axios";

export default {
  name: "FacultyTable",
  data() {
    return {
      columns: [
        {
          label: 'Name',
          field: 'name',
        },
        {
          label: 'Short name',
          field: 'shortName',
          type: 'number',
        },
      ],
      rows: [],
    }
  },
  methods: {
    deleteItem() {
      let selected_ids = Array.from(this.$refs['table'].selectedRows).map(item => item.id);

      const params = new URLSearchParams();
      selected_ids.forEach(id => params.append('ids', id))

      axios.delete("/api/faculties?" + params.toString())
          .then(request => {
            request.status === 200 ? this.$router.push({name: "successPage"}) : console.log(request);
            request.status === 500 ? this.$router.push({name: "serverErrorPage"}) : console.log(request);
          }).catch(e => {
        e.response.status === 405 ? this.$router.push({name: "methodNotAllowed"}) : this.$router.push({name: "serverErrorPage"});
      })
    },
    changeItem() {
      let selected = this.$refs['table'].selectedRows[0];
      let id = selected.id;

      this.$router.push({
        name: "updateFaculty",
        params: {id: id},
      })

    }
  },
  mounted() {
    axios.get("/api/faculties")
        .then(response => {
          this.rows = response.data;
        })
        .catch(e => {
          console.log(e)
          this.$router.push({name: "serverErrorPage"})
        })
  }
}
</script>

<style scoped>
</style>