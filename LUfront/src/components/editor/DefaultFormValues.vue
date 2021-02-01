<template>
  <div>
    <div
      v-for="(field, index) in formFieldsDTO.formFields"
      :key="index * 1.285"
    >
      <div class="mt-2">{{ field.defaultValue }}</div>
      <div v-if="field.type.name == 'boolean'">
        <v-tooltip bottom color="black">
          <template #activator="{ on: tooltip }">
            <v-btn icon v-on="{ ...tooltip }" color="primary" @click="accept()">
              <v-icon>mdi-check</v-icon>
            </v-btn>
          </template>
          <div v-if="pageName == 'BookPreview'">
            <span class="primary--text">accept</span>
          </div>
          <div v-if="field.type.name == 'CheckBookPlagiarism'">
            <span class="primary--text">is plagiarism</span>
          </div>
        </v-tooltip>
        <v-tooltip bottom color="black">
          <template #activator="{ on: tooltip }">
            <v-btn icon v-on="{ ...tooltip }" color="primary" @click="deny()">
              <v-icon>mdi-close</v-icon>
            </v-btn>
          </template>
          <div v-if="pageName == 'BookPreview'">
            <span class="primary--text">deny</span>
          </div>
          <div v-if="field.type.name == 'CheckBookPlagiarism'">
            <span class="primary--text">not plagiarism</span>
          </div>
        </v-tooltip>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    formFieldsDTO: {},
    pageName: {},
  },
  methods: {
    accept() {
      this.$emit("accepted", this.formFieldsDTO);
    },
    deny() {
      this.$emit("denied", this.formFieldsDTO);
    },
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