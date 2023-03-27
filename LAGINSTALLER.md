## Lag en installer (frivillig)

I dette prosjektet er Maven konfigurert slik at vi kan lage en *installer*, slik at programmet ditt enkelt kan installeres på ulike datamaskiner.

**Mac**

Kjør *Maven -> Lifecycle -> install*

**Windows**

Forutsetninger:
* [.NET 3.5](https://www.microsoft.com/nb-no/download/details.aspx?id=21) er installert
* [WiX versjon 3](https://wixtoolset.org/docs/wix3/) er installert

Kjør deretter *Maven -> Lifecycle -> install*

Uansett operativsystem vil en installer legge seg i mappen `target/installer` når du har kjørt install-fasen til maven. Denne filen kan du dele med lillebror eller legge ut på hjemmesiden din, så kan andre kjøre programmet ditt på sin maskin uten å installere en IDE eller fikle med kommandolinjen. (PS: installeren vil bare fungere på samme operativsystem som den ble laget på).

> Du må justere innholdet i [pom.xml](./pom.xml) slik at det matcher ditt prosjekt (for eksempel bør du endre på *name* og *app.vendor*). Dersom du importerer spesielle pakker kan det være du må gjøre tilpasninger av scriptene i *make* -mappen også.
