<template>

  <v-app-bar color="teal" density="compact">
    <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>
    <v-toolbar-title>Rozklad</v-toolbar-title>
    <v-spacer></v-spacer>
  </v-app-bar>

  <v-navigation-drawer v-model="drawer" temporary>
    <v-list>
      <v-list-group>
        <template v-slot:activator="{ props }">
          <v-list-item
              v-bind="props"
              prepend-icon="mdi-chess-queen"
              title="Faculty"
          ></v-list-item>
        </template>

        <v-list-item
            v-for="([title, icon], i) in cruds"
            :key="i"
            :value="title"
            :title="title"
            :prepend-icon="icon"
            @click="this.$router.push({name: this.facultyLinks[i]});"
        ></v-list-item>
      </v-list-group>

      <v-list-group>
        <template v-slot:activator="{ props }">
          <v-list-item
              v-bind="props"
              prepend-icon="mdi-school"
              title="Department"
          ></v-list-item>
        </template>

        <v-list-item
            v-for="([title, icon], i) in cruds"
            :key="i"
            :value="title"
            :title="title"
            :prepend-icon="icon"
            @click="this.$router.push({name: this.departmentsLinks[i]})"
        ></v-list-item>
      </v-list-group>

      <v-list-group>
        <template v-slot:activator="{ props }">
          <v-list-item
              v-bind="props"
              prepend-icon="mdi-account-multiple"
              title="Group"
          ></v-list-item>
        </template>

        <v-list-item
            v-for="([title, icon], i) in cruds"
            :key="i"
            :value="title"
            :title="title"
            :prepend-icon="icon"
            @click="this.$router.push({name: this.groupLinks[i]})"
        ></v-list-item>
      </v-list-group>

      <v-list-group>
        <template v-slot:activator="{ props }">
          <v-list-item
              v-bind="props"
              prepend-icon="mdi-account"
              title="Students"
          ></v-list-item>
        </template>

        <v-list-item
            v-for="([title, icon], i) in cruds"
            :key="i"
            :value="title"
            :title="title"
            :prepend-icon="icon"
            @click="this.$router.push({name: this.studentLinks[i]})"
        ></v-list-item>
      </v-list-group>

      <v-list-group>
        <template v-slot:activator="{ props }">
          <v-list-item
              v-bind="props"
              prepend-icon="mdi-account-tie"
              title="Teachers"
          ></v-list-item>
        </template>

        <v-list-item
            v-for="([title, icon], i) in cruds"
            :key="i"
            :value="title"
            :title="title"
            :prepend-icon="icon"
            @click="this.$router.push({name: this.teacherLinks[i]})"
        ></v-list-item>
      </v-list-group>

      <v-list-group>
        <template v-slot:activator="{ props }">
          <v-list-item
              v-bind="props"
              prepend-icon="mdi-math-compass"
              title="Disciplines"
          ></v-list-item>
        </template>

        <v-list-item
            v-for="([title, icon], i) in cruds"
            :key="i"
            :value="title"
            :title="title"
            :prepend-icon="icon"
            @click="this.$router.push({name: this.disciplineLinks[i]})"
        ></v-list-item>
      </v-list-group>

      <v-list-group>
        <template v-slot:activator="{ props }">
          <v-list-item
              v-bind="props"
              prepend-icon="mdi-calendar-clock"
              title="Schedule"
          ></v-list-item>
        </template>

        <v-list-item
            v-for="([title, icon], i) in cruds"
            :key="i"
            :value="title"
            :title="title"
            :prepend-icon="icon"
            @click="this.$router.push({name: this.scheduleLink[i]})"
        ></v-list-item>
      </v-list-group>

      <v-list-item
          prepend-icon="frequently-asked-questions"
          title="FAQ"
          @click="this.$router.push({name: 'faq'})"
      ></v-list-item>
    </v-list>
  </v-navigation-drawer>

</template>

<script>
export default {
  name: "AppBar",
  data: () => ({
    drawer: false,
    group: null,
    opened: false,
    facultyLinks: [
      "addFaculty",
      "getFaculties",
    ],
    departmentsLinks: [
      "addDepartment",
      "getDepartments",
    ],
    groupLinks: [
      "addGroup",
      "getGroups",
    ],
    studentLinks: [
      "addStudent",
      "getStudents",
    ],
    teacherLinks: [
      "addTeacher",
      "getTeachers",
    ],
    disciplineLinks: [
      "addDiscipline",
      "getDisciplines",
    ],
    scheduleLink: [
      "addSchedule",
      "getSchedules",
    ],
    cruds: [
      ['Add new', 'mdi-plus-outline'],
      ['Find', 'mdi-magnify'],
    ],
  }),
}
</script>

<style scoped>
</style>