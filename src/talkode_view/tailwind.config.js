/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./pages/**/*.{html,js}",
    "./components/**/*.{html,js}",
    "./src/**/*.{tsx, css}",
  ],

  theme: {
    extend: {
      colors: {
        light: "#eeecff",
        gray: "#c8c5d4",
        "purple-3": "#643ED7",
        "purple-2": "#8060E0",
        "purple-1": "#A28AEB",
      },
      spacing: {
        100: "100%",
        "90vw": "90vw",
        "90vh": "90vh",
      },
    },
  },

  // ...
};
