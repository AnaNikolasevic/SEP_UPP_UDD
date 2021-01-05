<template>
  <nav>
    <!-- snackbar -->
    <v-snackbar v-model="snackbarSuccess" :timeout="4000" top color="success">
      <span>{{ snackbarSuccessText }}</span>
      <v-btn text @click="snackbarSuccess = false">Close</v-btn>
    </v-snackbar>

    <v-snackbar v-model="snackbarDanger" :timeout="4000" top color="danger">
      <span>{{ snackbarDangerText }}</span>
      <v-btn text @click="snackbarDanger = false">Close</v-btn>
    </v-snackbar>

    <!-- gornji toolbar -->
    <v-toolbar flat class="mx-12">
      <v-toolbar-title>
        <span color="orange-text">PAYMENT CONCETRATOR</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
     
    </v-toolbar>
  </nav>
</template>

<script>
export default {
  data() {
    return {
      snackbarSuccess: false,
      snackbarSuccessText: "",
      snackbarDanger: false,
      snackbarDangerText: "",
    };
  },
};
</script>