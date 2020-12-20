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
        <span color="orange-text">BOLNICA</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <div class="mx-2">
        <LoginComponent
          @loggedIn="
            snackbarSuccess = true;
            snackbarSuccessText = 'You are logged in!';
          "
          @notLoggedIn="
            snackbarDanger = true;
            snackbarDangerText = 'Can not log in. There is no such user!';
          "
        />
      </div>
      <div class="mx-2">
        <RegistrationComponent
          @registered="
            snackbarSuccess = true;
            snackbarSuccessText = 'You are registered! Please login.';
          "
          @notRegistered="
            snackbarDanger = true;
            snackbarDangerText = 'Can not register.';
          "
        />
      </div>
    </v-toolbar>
  </nav>
</template>

<script>
import LoginComponent from "@/components/homePage/LoginComponent.vue";
import RegistrationComponent from "@/components/homePage/RegistrationComponent.vue";
export default {
  components: {
      LoginComponent, RegistrationComponent
  },
data() {
    return {
      snackbarSuccess: false,
      snackbarSuccessText: "",
      snackbarDanger: false,
      snackbarDangerText: "",
    };
 },
}

</script>