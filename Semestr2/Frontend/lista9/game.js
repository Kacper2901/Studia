"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var Endpoints;
(function (Endpoints) {
    Endpoints["ELIXIRS"] = "Elixirs";
    Endpoints["SPELLS"] = "Spells";
})(Endpoints || (Endpoints = {}));
let elixirs = [];
let spells = [];
let validOption = undefined;
const gameContainer = document.getElementById("game");
function fetchData(endpoint) {
    return __awaiter(this, void 0, void 0, function* () {
        const response = yield fetch(`https://wizard-world-api.herokuapp.com/${endpoint}`);
        if (!response.ok) {
            throw new Error(`Error fetching data from ${endpoint}`);
        }
        const data = yield response.json();
        return data;
    });
}
function fetchAllData() {
    return __awaiter(this, void 0, void 0, function* () {
        const [elixirsResponse, spellsResponse] = yield Promise.all([
            fetchData(Endpoints.ELIXIRS),
            fetchData(Endpoints.SPELLS),
        ]);
        elixirs = elixirsResponse.filter((elixir) => elixir.effect);
        spells = spellsResponse.filter((spell) => spell.incantation);
    });
}
function getRandomElements(array, count) {
    const indexes = new Set();
    while (indexes.size < count) {
        const randomIndex = Math.floor(Math.random() * array.length);
        indexes.add(randomIndex);
    }
    return Array.from(indexes).map((index) => array[index]);
}
function generateOptions(randomElements) {
    const [rightOption, ...rest] = randomElements;
    const options = [rightOption, ...rest].sort(() => Math.random() > 0.5 ? 1 : -1);
    return {
        rightOption,
        options,
    };
}
function elixirGame() {
    const { options, rightOption } = generateOptions(getRandomElements(elixirs, 3));
    validOption = rightOption.name;
    console.log(`Cheat Mode: Right answer is ${validOption}`);
    renderOptions(`Which elixir effect is: "${rightOption.effect}"?`, options.map((elixir) => elixir.name));
}
function spellGame() {
    const { options, rightOption } = generateOptions(getRandomElements(spells, 3));
    validOption = rightOption.name;
    console.log(`Cheat Mode: Right answer is ${validOption}`);
    renderOptions(`Which spell incantation is: "${rightOption.incantation}"?`, options.map((spell) => spell.name));
}
function renderOptions(question, answers) {
    const questionElement = document.getElementById("question");
    if (!gameContainer || !questionElement) {
        throw new Error("Game container or question element not found");
    }
    gameContainer.innerHTML = "";
    questionElement.textContent = question;
    answers.forEach((answer) => {
        const option = document.createElement("button");
        option.textContent = answer;
        gameContainer.appendChild(option);
    });
}
gameContainer.addEventListener("click", (event) => {
    const target = event.target;
    if (target.tagName === "BUTTON") {
        const selectedOption = target.textContent;
        if (selectedOption === validOption) {
            round();
        }
        else {
            alert("Wrong answer!");
        }
    }
});
function round() {
    const randomGame = Math.random() > 0.5 ? elixirGame : spellGame;
    randomGame();
}
function startGame() {
    return __awaiter(this, void 0, void 0, function* () {
        yield fetchAllData();
        round();
    });
}
startGame();
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiZ2FtZS5qcyIsInNvdXJjZVJvb3QiOiIiLCJzb3VyY2VzIjpbImdhbWUudHMiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6Ijs7Ozs7Ozs7OztBQUFBLElBQUssU0FHSjtBQUhELFdBQUssU0FBUztJQUNWLGdDQUFtQixDQUFBO0lBQ25CLDhCQUFpQixDQUFBO0FBQ3JCLENBQUMsRUFISSxTQUFTLEtBQVQsU0FBUyxRQUdiO0FBY0QsSUFBSSxPQUFPLEdBQWEsRUFBRSxDQUFDO0FBQzNCLElBQUksTUFBTSxHQUFZLEVBQUUsQ0FBQztBQUV6QixJQUFJLFdBQVcsR0FBdUIsU0FBUyxDQUFDO0FBRWhELE1BQU0sYUFBYSxHQUFHLFFBQVEsQ0FBQyxjQUFjLENBQUMsTUFBTSxDQUFFLENBQUM7QUFFdkQsU0FBZSxTQUFTLENBQUMsUUFBbUI7O1FBQ3hDLE1BQU0sUUFBUSxHQUFHLE1BQU0sS0FBSyxDQUN4QiwwQ0FBMEMsUUFBUSxFQUFFLENBQ3ZELENBQUM7UUFDRixJQUFJLENBQUMsUUFBUSxDQUFDLEVBQUUsRUFBRTtZQUNkLE1BQU0sSUFBSSxLQUFLLENBQUMsNEJBQTRCLFFBQVEsRUFBRSxDQUFDLENBQUM7U0FDM0Q7UUFFRCxNQUFNLElBQUksR0FBRyxNQUFNLFFBQVEsQ0FBQyxJQUFJLEVBQUUsQ0FBQztRQUNuQyxPQUFPLElBQUksQ0FBQztJQUNoQixDQUFDO0NBQUE7QUFFRCxTQUFlLFlBQVk7O1FBQ3ZCLE1BQU0sQ0FBQyxlQUFlLEVBQUUsY0FBYyxDQUFDLEdBQUcsTUFBTSxPQUFPLENBQUMsR0FBRyxDQUFDO1lBQ3hELFNBQVMsQ0FBQyxTQUFTLENBQUMsT0FBTyxDQUFDO1lBQzVCLFNBQVMsQ0FBQyxTQUFTLENBQUMsTUFBTSxDQUFDO1NBQzlCLENBQUMsQ0FBQztRQUVILE9BQU8sR0FBSSxlQUE0QixDQUFDLE1BQU0sQ0FBQyxDQUFDLE1BQU0sRUFBRSxFQUFFLENBQUMsTUFBTSxDQUFDLE1BQU0sQ0FBQyxDQUFDO1FBQzFFLE1BQU0sR0FBSSxjQUEw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