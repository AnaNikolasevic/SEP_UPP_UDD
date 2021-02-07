<template>
  <div class="back">
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
                <br/>
                <div>
                    <v-btn title="Open" color="primary" :href="book.path" download>
                    <v-icon>mdi-file</v-icon>
                    </v-btn>
                </div>
              </v-card-text>       
            </div>
          </v-card>
        </v-flex>
      </v-layout>
  </div>
</template>

<script>
//import axios from "axios";
export default {
  data() {
    return {
      booksInCart : [],
      status: "",
      books: []
      
    };
  },
  methods:{
    showBooks(){
        this.books = this.$store.state.purchasedBooks;
        console.log("kupljene knjige")
        console.log(this.$store.state.purchasedBooks)
    },
  },
  mounted() {
     this.showBooks();
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