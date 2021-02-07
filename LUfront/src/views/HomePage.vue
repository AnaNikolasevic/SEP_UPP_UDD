<template>
  <div class="back">
    <v-snackbar v-model="snackbarSuccess" :timeout="4000" top color="success">
      <span>{{ snackbarSuccessText }}</span>
      <v-btn text @click="snackbarSuccess = false">Close</v-btn>
    </v-snackbar>

    <v-snackbar v-model="snackbarDanger" :timeout="4000" top color="danger">
      <span>{{ snackbarDangerText }}</span>
      <v-btn text @click="snackbarDanger = false">Close</v-btn>
    </v-snackbar>
          <!-- kartice -->
      <v-layout row wrap>
        <v-flex xs12 sm6 md4 lg4 v-for="book in books" :key="book.id">
          <v-card hover elevation="2" class="text-center ma-6">
            <div class="cardBorderColor">
              <v-card-title>Author: {{book.author}}</v-card-title>
              <v-card-text>
                <div
                  class="primary--text font-weight-bold headline"
                >{{book.title}}</div>
                 <div display-1 text--primary>{{ book.synopsis }}</div>
                <div class="text font-weight-bold headline">
                    $ {{ book.price}}
                </div>
              </v-card-text>
              <v-card-actions>
                  <template >
                    <v-btn  color="primary" @click="addToChart(book)"> Buy
                      <v-icon right>mdi-cart</v-icon>
                    </v-btn>
                  </template> 
              </v-card-actions>       
            </div>
          </v-card>
        </v-flex>
      </v-layout>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Home",
  data() {
    return {
      books : [],
      snackbarSuccess: false,
      snackbarSuccessText: "",
      snackbarDanger: false,
      snackbarDangerText: "",
      
    };
  },
  methods:{
    addToChart(book){
      console.log("dodala u korpu")
      this.$store.commit("addBooksInCart", book)
      this.snackbarSuccessText = "Book is successfully add in crat"
      this.snackbarSuccess = true
      console.log(this.$store.state.booksInCart);
    }
  },
  mounted() {
     axios
        .get("http://localhost:8080/bookPrototype")
        .then((response) => {
          this.books = response.data;
          console.log(response.data);

        })
        .catch((error) => {
          console.log(error);
        });
  },
};
</script>
<style scoped>
.cardBorderColor {
  border-left: 1px solid #9575CD;
  border-top: 1px solid #9575CD;
  border-right: 1px solid #9575CD;
  border-bottom: 1px solid #9575CD;
}
</style>
