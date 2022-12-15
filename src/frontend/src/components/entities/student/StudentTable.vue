<template>
  <v-card elevation="0">
    <v-card-title>
      <h1>Students</h1>
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
  name: "StudentTable",
  data() {
    return {
      columns: [
        {
          label: 'Name',
          field: 'name',
        },
        {
          label: 'Group',
          field: 'group',
        },
        {
          label: 'Email',
          field: 'email',
        },
        {
          label: 'Phone',
          field: 'phone',
        },
      ],
      rows: [
        {id: 1, name: "Gisselle", group: "IA-11", email: "аarchibald.fay@gmailll.org", phone: "050-623-6131", group_id: 1},
        {id: 2, name: "Dereck", group: "IA-11", email: "аshyanne07@gasssmail.com", phone: "035-289-3199", group_id: 2},
        {id: 3, name: "Rollin", group: "IC-12", email: "аkschulist@dailyladylog.com", phone: "035-289-3199", group_id: 3},
        {id: 4, name: "Gisselle", group: "IT-14", email: "аgeovanny.tromp@curcuplas.me", phone: "035-289-3199", group_id: 2},
        {id: 5, name: "Toy", group: "IT-12", email: "аalysa02@dailyladylog.com", phone: "035-289-3199", group_id: 1},
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
      let email = selected.email;
      let phone = selected.phone;
      let group_id = selected.group_id;

      this.$router.push({
        name: "updateStudent",
        params: {id: id},
        query: {id: id, name: name, email: email, phone: phone, group_id: group_id},
      })

    }
  }
}
</script>

<style scoped>

</style>