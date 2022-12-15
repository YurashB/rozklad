<template>
  <v-app>
    <AppBar/>

    <v-main
    :scrollable="true">
        <router-view>
        </router-view>
    </v-main>
  </v-app>
</template>

<script>
import AppBar from "@/components/bars/AppBar";

export default {
  name: 'App',
  components: {
    AppBar
  },
}
</script>

