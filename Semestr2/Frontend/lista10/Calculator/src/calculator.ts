import { evaluate } from 'mathjs';

export class Calculator {
    private expression: string = '';

    /** dodaje znak do wyrazenia */
    public append(value: string): string {
        this.expression += value;
        return this.expression;
    }

    /** uduwa ostatni znak */
    public deleteLast(): string {
        this.expression = this.expression.slice(0, -1);
        return this.expression || '0';
    }

    /** czysci cale wyraznie */
    public clear(): string {
        this.expression = '';
        return '0';
    }

    /** oblicza wynik*/
    public calculate(): string {
        try {
            const result = evaluate(this.expression);
            this.expression = result.toString();
            return this.expression;
        } catch {
            return 'Error';
        }
    }

    /** zwraca biezace wyrazenie (lub 0 jeśli puste) */
    public getExpression(): string {
        return this.expression || '0';
    }
}
