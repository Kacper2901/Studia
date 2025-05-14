import { Calculator } from './calculator';
import './style.css';

// const unused = 2;
const calc = new Calculator();
const display = document.getElementById('display') as HTMLDivElement; //bierze display z html (wynik)
const buttons = document.querySelectorAll<HTMLButtonElement>('.calculator__button'); //bierze  przyciski
buttons.forEach(button => {
  button.addEventListener('click', () => {
    const value = button.dataset.value;
    const action = button.dataset.action;

    if (value) {
      display.textContent = calc.append(value);
    } else if (action === 'delete') {
      display.textContent = calc.deleteLast();
    } else if (action === 'clear') {
      display.textContent = calc.clear();
    } else if (action === 'calculate') {
      display.textContent = calc.calculate();
    }
  });
});
