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
      <div class="mx-2"  v-if="this.$store.state.user.role == 'writer'">
        <AddBook />
      </div>
      <v-btn
        text
        color="primary"
        @click="openKP()"
        v-if="this.$store.state.user.role == 'none'"
      >
        <span>Add payment</span>
        <v-icon right>mdi-plus</v-icon>
      </v-btn>
      <v-btn
        text
        color="primary"
        @click="openCart()"
        v-if="this.$store.state.user.role == 'none'"
      >
        <span>Cart</span>
        <v-icon right>mdi-cart</v-icon>
      </v-btn>
      <v-btn
        text
        color="primary"
        @click="mandatoryUpload()"
        v-if="this.$store.state.user.role=='writer'"
      >
        <span>Mandatory upload</span>
        <v-icon right>mdi-book</v-icon>
      </v-btn>
      <v-btn
        text
        color="primary"
        @click="manageWriters()"
        v-if="this.$store.state.user.role=='boardMember'"
      >
        <span>Mandatory books</span>
        <v-icon right>mdi-people</v-icon>
      </v-btn>
      <v-btn
        text
        color="primary"
        v-if="this.$store.state.user.role == 'editor'"
      >
        <span @click="openBookPreview()">Book Preview</span>
        <v-icon right>mdi-book</v-icon>
      </v-btn>
      <v-btn
        text
        color="primary"
        v-if="this.$store.state.user.role == 'editor'"
      >
        <span @click="openBookPlagiarism()">Check Book Plagiarsim</span>
      </v-btn>
      <v-btn text color="primary" v-if="this.$store.state.user.role != 'none'">
        <span @click="logout()">Logout</span>
        <v-icon right>mdi-close</v-icon>

      </v-btn>
    </v-toolbar>
  </nav>
</template>

<script>
import LoginComponent from "@/components/homePage/LoginComponent.vue";
import RegistrationComponent from "@/components/homePage/RegistrationComponent.vue";
import AddBook from "@/components/homePage/WriterComponents/AddBookForm.vue";

export default {
  components: {
    LoginComponent,
    RegistrationComponent,
    AddBook,
  },
  data() {
    return {
      snackbarSuccess: false,
      snackbarSuccessText: "",
      snackbarDanger: false,
      snackbarDangerText: "",
    };
  },
  methods: {
    openCart() {
      this.$router.push("/cart");
    },
    mandatoryUpload() {
      this.$router.push("/uplaodMandatory");
    },
    // uploadMandatory(){
    //   this.$router.push("/uplaodMandatory");
    // },
    manageWriters() {
      this.$router.push("/mandatoryBooks");
    },
    openKP() {
      window.open("http://localhost:8083/addPayment/?idLU=" + 1);
    },
    openBookPreview() {
      this.$router.push("/bookPreview");
    },
    openBookPlagiarism(){
       this.$router.push("/bookPlagiarism");
    },
    openUploadBook(){
         this.$router.push("/uploadBook");
    },
    logout() {
      localStorage.removeItem("user");
      this.$store.commit("logout");
      this.$store.commit("deleteAll");
      this.snackbarSuccess = true;
      this.snackbarSuccessText = "You are logged out";
      this.LoginDialog = false;
      this.$router.push("/");
      location.reload();
    },
  },
};
</script>