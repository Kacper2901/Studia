import globals from "globals"; //zmienne globalne przegladarki (window, documetn,alert)
import tseslint from "typescript-eslint"; //obsluga typescript (np parser skladni)
import { defineConfig } from "eslint/config"; //

export default defineConfig([ //eksportujemy ta tablice jako konfiguracja
  {
    files: ["**/*.ts"], //pliki z rozszerzeniem .ts niezaleznie od folderu
    languageOptions: {
      parser: tseslint.parser, //parser analizuje a nie JS
      parserOptions: {//najnowsza skladnia
        ecmaVersion: "latest",
        sourceType: "module",
      },
      globals: globals.browser, //nie bedzie bledow podczas dzialania w przegladarce
    },
    plugins: {
      "@typescript-eslint": tseslint.plugin, //
    },
    rules: {
      // tu mozna dodac własne reguly
    }
  },
  ...tseslint.configs.recommended, //rekomendowane reguly dla ts
]);


//wykrywa bledy skladniowe (niektore bledy mozna naprawic za pomoca --fix)
//w configu ustawiamy ktore pliki ma sprawdzac, jakiego parsera uzyc, srodowisko(np przegladarka)