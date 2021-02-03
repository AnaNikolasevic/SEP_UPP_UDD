<template>
  <div>
    <div
      v-for="(field, index) in formFieldsDTO.formFields"
      :key="index * 1.285"
    >
      <div class="mt-2" v-if="field.type.name == 'string'">
        {{ field.defaultValue }}
      </div>
      <div v-if="field.type.name == 'multiEnum_betaReaders'">
        <v-combobox
          :items="Object.keys(field.type.values)"
          :label="field.label"
          v-model="field.fieldValue"
          outlined
          dense
          multiple
        ></v-combobox>
      </div>
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
          <div v-if="pageName == 'CheckBookPlagiarism'">
            <span class="primary--text">is plagiarism</span>
          </div>
          <div v-if="pageName == 'ChooseToSendToBetaReaders'">
            <span class="primary--text">send to beta-readers</span>
          </div>
          <div v-if="pageName == 'ChooseBetaReaders'">
            <span class="primary--text">send</span>
          </div>
        </v-tooltip>
        <template v-if="pageName != 'ChooseBetaReaders'">
          <v-tooltip bottom color="black">
            <template #activator="{ on: tooltip }">
              <v-btn icon v-on="{ ...tooltip }" color="primary" @click="deny()">
                <v-icon>mdi-close</v-icon>
              </v-btn>
            </template>
            <div v-if="pageName == 'BookPreview'">
              <span class="primary--text">deny</span>
            </div>
            <div v-if="pageName == 'CheckBookPlagiarism'">
              <span class="primary--text">is not plagiarism</span>
            </div>
            <div v-if="pageName == 'ChooseToSendToBetaReaders'">
              <span class="primary--text">don't send to beta-readers</span>
            </div>
          </v-tooltip>
        </template>
      </div >
      <div v-else-if="field.type.name == 'file_upload'">
            <v-file-input
               label="Choose pdf"
               truncate-length="15"
               v-model="field.fieldValue"
            ></v-file-input>
      </div>
      <div v-else-if="field.type.name == 'file_view'">
        <v-btn title="Open" color="primary" :href="field.value.value" download>                     
        <v-icon>mdi-file</v-icon>
      </v-btn>
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
      this.$emit("accepted", this.formFieldsDTO, this.formFieldsDTO.formFields);
    },
    deny() {
      this.$emit("denied", this.formFieldsDTO, this.formFieldsDTO.formFields);
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