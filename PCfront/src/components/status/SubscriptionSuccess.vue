<template>
  <div>
    <h1>You have successfully subscribed for audiobooks!</h1>
    <v-btn text color="primary" @click="redirectToLU()">Go back to LU</v-btn>
  </div>
</template>
<script>
//import axios from "axios";
export default {
  data: () => ({}),
  methods: {
    redirectToLU() {
      window.location = window.open("http://localhost:8084");
    },
  },
  mounted() {},
};
</script>