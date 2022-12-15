<template>
      <div class="faq">
        <details
            v-for="faq in faqs" :key="faq">
          <summary v-text="faq.question"></summary>
          <div class="faq-content">
            <p v-text="faq.answer"></p>
          </div>
        </details>
      </div>
  <div>
    <vue-good-table
        :columns="columns"
        :rows="rows"/>
  </div>
</template>

<script>
export default {
  name: "FAQ",
  data: () => ({
  faqs: [
    {
      question: "asdfghj",
      answer: "sdfghjkl"
    },
    {
      question: "asdfghj",
      answer: "sdfghjkl"
    },
    {
      question: "asdfghj",
      answer: "sdfghjkl"
    }
  ]
  }),
}
</script>

<style scoped>
p {
padding-bottom: 30px;
}
.faq-content {
  margin: 30px;
}

details {
  background-color: #00695C;
  color: #fff;
  font-size: 23px;
  margin: 15px;
}

summary {
  padding: 20px 35px;
  list-style: none;
  display: flex;
  justify-content: space-between;
  transition: height 1s ease;
}

summary::-webkit-details-marker {
  display: none;
}

summary:after {
  content: "\002B";
}

details[open] summary {
  border-bottom: 5px solid #eae9e9;
}

details[open] summary:after {
  content: "\00D7";
}
</style>