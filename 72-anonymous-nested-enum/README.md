# Classi anonime, classi innestate, enumerazioni

**Fare correggere ad ogni parte dell'esercizio, non solo al termine di tutte le parti.**

## Parte 1: classe anonima

Si osservi la classe Function, che modella una singola funzione con un solo input ed un solo output di tipo arbitrario.
Si implementi il metodo `identity()`, che deve restituire la funzione identià
(ossia, che restituisce l'input passato in ingresso senza modifica alcuna)
tramite una classe anonima.
Si osservi `TestFunctionalLibrary` per trovare esempi di classi anonime che implementano function.
Ci si prepari a rispondere alla seguente domanda al momento della correzione:
> perché `identity()` è un metodo, e non una costante `public static`?

Il perché è proprio dovuto a come funziona le type variable, `identity()` possiede T come type variable e fa in modo che il valore di ritorno sia uguale a quello in entrata. Tuttavia è più interessante discutere il caso contrario, cioè come sarebbe stato se `identity()` fosse stata una costante. Quello che succederebbe è che tramite la type variable saremmo costretti a specificare il tipo di oggetto a cui ci stiamo riferendo. Scrivendo per esempio `static final Function<T,T> IDENTITY = ...`, noteremo che il compilatore sottolinea la T come `Cannot make a static reference to the non-static type I` e quindi saremmo costretti a specificare il tipo di oggetto con cui ci stiamo riferendo. Se fosse stato `static final Function<Object, Object> IDENTITY = ...` questo provocherebbe alcuni warning (come cast o uso di raw types) che riducono la qualità del codice.

## Parte 2: sfruttare le classi anonime per costruire una libreria funzionale

Si implementino le funzioni di utilità non ancora implementate all'interno di `Transformers`.
Queste funzioni rappresentano manipolazioni di tipo *funzionale* di collezioni.
Si leggano con attenzione i commenti Javadoc presenti per trovare la soluzione più compatta per implementare le funzioni
richieste.
Si minimizzino le duplicazioni di codice, e non si utilizzino metodi "di appoggio".

## Parte 3: classi innestate ed enum

All'interno della classe MonthSorterNested, si crei una `enum Month` che modella i mesi dell'anno.
Si suggerisce di valutare l'utilizzo di un campo che modella il numero di giorni del mese.
Questa enum *deve* avere un metodo `Month fromString(String)` che, data una stringa di testo, restituisce il `Month`
che meglio la rappresenta. A tal proposito, si legga con molta attenzione la Javadoc di `MonthSorter`.

Utilizzare questa `enum` come supporto per la costruzione di due classi innestate: `SortByMonthOrder` e `SortByDate`
che implementano `Comparator<String>` e rappresentano, rispettivamente, un comparatore che ordina delle stringhe
(interpretandole come mesi) in base al loro ordine nell'anno, ed un comparatore che le ordina invece in base al numero
di giorni che il mese ha.
