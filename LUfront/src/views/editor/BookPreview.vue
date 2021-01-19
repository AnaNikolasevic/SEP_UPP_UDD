<template>
  <div>
    <!-- Snackbar -->
    <v-snackbar v-model="snackbarSuccess" :timeout="3500" top color="success">
      <span>{{ snackbarSuccessText }}</span>
      <v-btn text @click="snackbarSuccess = false">Close</v-btn>
    </v-snackbar>
    <v-snackbar v-model="snackbarDanger" :timeout="3500" top color="danger">
      <span>{{ snackbarDangerText }}</span>
      <v-btn text @click="snackbarDanger = false">Close</v-btn>
    </v-snackbar>
    <v-container class="mt-12 pt-2">
      <v-layout row wrap>
        <v-flex
          xs12
          sm6
          md4
          lg10
          ml-12
          v-for="bookPreview in bookPreviews"
          :key="bookPreview.id - 300"
        >
          <v-card hover elevation="2" class="text-center ma-6">
            <div class="detailsBorderColor">
              <v-card-title></v-card-title>
              <v-card-text>
                <div class="primary--text font-weight-bold headline">
                  {{ bookPreview.title }}
                </div>
                <div class="mt-2 font-italic">
                  genre: {{ bookPreview.genre }}
                </div>
                <div class="mt-2">{{ bookPreview.synopsis }}</div>
              </v-card-text>
            </div>
          </v-card>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      snackbarSuccess: false,
      snackbarSuccessText: "",
      snackbarDanger: false,
      snackbarDangerText: "",
      bookPreviews: [],
    };
  },
  methods: {
    getBookPreviews() {
      axios
        .get(
          "http://localhost:8080/bookPrototype/editor/" +
            this.$store.state.user.username
        )
        .then((bookPreviews) => {
          this.bookPreviews = bookPreviews.data;
          console.log(this.bookPreviews.length());
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },

  mounted() {
    this.getBookPreviews();
  },
};
</script>

<style scoped>
.cardBorderColor {
  border-left: 1px solid #9575cd;
  border-top: 1px solid #9575cd;
  border-right: 1px solid #9575cd;
  border-bottom: 1px solid #9575cd;
}
.detailsBorderColor {
  border-left: 2px solid #9575cd;
  border-top: 2px solid #9575cd;
  border-right: 2px solid #9575cd;
  border-bottom: 2px solid #9575cd;
}
</style>