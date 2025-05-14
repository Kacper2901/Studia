// obiekt endpoints na enum
enum Endpoints {
    ELIXIRS = "Elixirs",
    SPELLS = "Spells",
}

//typy elixir i spell
type Elixir = {
    id: string;
    name: string;
    effect: string;
};

type Spell = {
    id: string;
    name: string;
    incantation: string;
};

//typy do tablic
let elixirs: Elixir[] = [];
let spells: Spell[] = [];

//typ do validOption
let validOption: string | undefined = undefined;

//operator Non-null Assertion (!) dla gameContainer
const gameContainer = document.getElementById("game")!;

//typ endpoint jako Endpoints w funkcji fetchData
async function fetchData(endpoint: Endpoints): Promise<any[]> { //funkjca asynchroniczna zwraca promimse (czeka na odpowiedz serwera dzieki await)
    const response = await fetch(
        `https://wizard-world-api.herokuapp.com/${endpoint}`
    );
    if (!response.ok) {
        throw new Error(`Error fetching data from ${endpoint}`);
    }

    const data = await response.json();
    return data;
}

async function fetchAllData() {
    const [elixirsResponse, spellsResponse] = await Promise.all([
        fetchData(Endpoints.ELIXIRS),
        fetchData(Endpoints.SPELLS),
    ]);

    //elixir spell odpowiedni typ w filter
    elixirs = (elixirsResponse as Elixir[]).filter((elixir) => elixir.effect);
    spells = (spellsResponse as Spell[]).filter((spell) => spell.incantation);
}

//typy funkcji getRandomElements (generyczna + count: number)
function getRandomElements<T>(array: T[], count: number): T[] {
    //typ Set<number> zmiennej indexes
    const indexes: Set<number> = new Set();

    while (indexes.size < count) {
        const randomIndex = Math.floor(Math.random() * array.length);
        indexes.add(randomIndex);
    }

    return Array.from(indexes).map((index) => array[index]);
}

//typ argumentowi funkcji generateOptions (generyczna)
function generateOptions<T extends { name: string }>(
    randomElements: T[]
): { rightOption: T; options: T[] } {
    const [rightOption, ...rest] = randomElements;

    const options = [rightOption, ...rest].sort(() =>
        Math.random() > 0.5 ? 1 : -1
    );

    return {
        rightOption,
        options,
    };
}

function elixirGame() {
    const { options, rightOption } = generateOptions(
        getRandomElements(elixirs, 3)
    );

    validOption = rightOption.name;

    console.log(`Cheat Mode: Right answer is ${validOption}`);

    renderOptions(
        `Which elixir effect is: "${rightOption.effect}"?`,
        options.map((elixir) => elixir.name)
    );
}

function spellGame() {
    const { options, rightOption } = generateOptions(
        getRandomElements(spells, 3)
    );

    validOption = rightOption.name;

    console.log(`Cheat Mode: Right answer is ${validOption}`);

    renderOptions(
        `Which spell incantation is: "${rightOption.incantation}"?`,
        options.map((spell) => spell.name)
    );
}

//typy argumentow funkcji renderOptions
function renderOptions(question: string, answers: string[]): void {
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

// popraw EventListener: dodaj Non-null Assertion (!) i rzutowanie target na HTMLElement
gameContainer.addEventListener("click", (event: MouseEvent) => {
    const target = event.target as HTMLElement;

    if (target.tagName === "BUTTON") {
        const selectedOption = target.textContent;

        if (selectedOption === validOption) {
            round();
        } else {
            alert("Wrong answer!");
        }
    }
});

function round() {
    const randomGame = Math.random() > 0.5 ? elixirGame : spellGame;
    randomGame();
}

async function startGame() {
    await fetchAllData();
    round();
}

startGame();
