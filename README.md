# SmartKeplerMirror

## 0. Einführung
Bilder des Smart Mirrors:
![Smart Mirror Picture 1](https://raw.githubusercontent.com/Bauinger/Bauinger-smartkeplermirror/main/docassets/1.jpeg)
![Smart Mirror Picture](https://raw.githubusercontent.com/Bauinger/Bauinger-smartkeplermirror/main/docassets/2.jpeg)
### Bauanleitung für den Smart Mirror Kepler
Wenn du einen Smart Mirror bauen willst,
findest du [hier]() eine Bauanleitung.

Kurze Information zum REST Service:
Der REST Service und das frontend sind beide voll funktionsfähig, aber der Raspberry PI ist nicht leistungsfähig genug um den REST Service laufen zu lassen (Startupzeiten von bsi zu 20 min und Timeouts). Aus diesem Grund wurde eine [NeDB](https://github.com/louischatriot/nedb), das ist eine built-in NodeJS-Datenbank, statt des REST Service verwendet.

Weiters wurde anstatt eines NodeJS Servers ein NGINX Webserver verwendet, weil dass aufetzen so um einiges einfacher war.

## 1. Aufsetzen des Raspberry PI
### Benötigte Ressourcen
* Raspberry PI 3 (Nicht getestet auf 1 & 2 / 4 ist nicht kompatibel mit chilipe-kiosk)
* Micro-SD-Karte (4+ GB)
* Maus und Tastatur (für Bedienung des RPI)
* Bildschirm und HDMI Kabel (Kann auch dirket auf dem SmartMirror konfiguriert werden)
* PC (für Installation des Betriebsystems)

### Installation des Betriebsystems
1. Lade dir den Latest Release von chilipie-kisok [hier](https://github.com/futurice/chilipie-kiosk/releases) herunter. (Getestet mit chilipie-kiosk-2.1.0.img.tar.gz)
2. Lade dir den ImageFlasher [Etcher](https://www.balena.io/etcher/) herunter.
3. Entpacke die heruntergeladene Imagedatei mit 7z oder einem anderem Programm. 
4. Verwende Balena um die entpackte Imagedatei auf deine SD-Karte zu flashen.
### Konfiguration des Raspberry PIs
1. Schiebe die SD-Karte in den Slot.
2. Schließe die Maus, die Tastatur und den Bildschirm an.
3. Schließe den Raspberry PI mit dem Kabel an den Strom an.
4. Danach startet der Raspberry PI automatisch, warte bis dieser vollständig hochgefahren ist.
5. Drücke die Tasten:
> STRG + ALT + F2
6. Um die Konfiguration zu erleichtern wähle "Localisation Options".
7. Konfiguriere das Tastaturlayout (Modellauswahl nebensächlich).
8. Wähle im Menü den Reiter "Network Options".
9. Konfiguriere das Netzwerk.
#### Installation von NodeJS und NPM
10. Drücke die Tasten:
> STRG + ALT + F3
11. Führe die drei Bash-Befehle aus:
````bash
curl https://deb.nodesource.com/setup_14.x | sudo -E bash -
sudo apt-get update
sudo apt install nodejs
````
12. Tippe jetzt in die Konsole:
````bash
nodejs -v
npm -v
````
13. Es sollten jetzt Versionsverweise eingeblendet werden. (Für Hilfe [hier](https://github.com/nodesource) klicken)

#### Installation von NGINX
1. Führe folgenden Bash-Befehl aus:
````bash
sudo apt install nginx
````
2. Anschließend überprüfe ob die Installation erfolgreich war und ob der Webserver läuft mit:
````bash
service nginx status
````
3. Führe den Befehl aus:
````bash
ifconfig
````
4. Unter inet-adress findest du deine IP-Adresse (z.B.: 192.168.3.45) notiere diese.
5. Drücke die Tasten:
> STRG + ALT + F1
6. Beende den Vollbildmodus mit der Taste:
> F11
7. Tippe in der Adresszeile folgendes ein (ersetze 'meine-IP-Adresse' durch die vorher notierte Adresse):
````bash
http://meine-IP-Adresse
````
8. Nachdem du dies Seite geöffnet hat müsste eine Seite erscheinen auf der "Welcome to nginx!" steht.
9. Drücke die Tasten:
> STRG + ALT + F3
10. Anschließend fügen wir ngnix noch zu systemctl hinzu um bei jedem Neustart des Raspberry Pis auch den Server zu starten:
````bash
sudo systemctl enable nginx
````

#### Allow CORS Plugin 
1. Drücke die Tasten:
> STRG + ALT + F1
2. Suche nach folgendem Link https://chrome.google.com/webstore/detail/allow-cors-access-control/lhobafahddgcelffkeicbaginigeejlf?hl=de
3. Installiere das Plugin und aktiviere es
#### Code von Github Klonen
1. Installiere git mit:
````bash
sudo apt install git
`````
2. Klone das Repository von Git mit:
````bash
git clone https://github.com/Bauinger/Bauinger-smartkeplermirror.git
````
## 2. Installation des Frontends
1. Wechsle in das Verzeichnis:
````bash
cd Bauinger-smartkeplermirror/frontend/smart-kepler
````
2. Führe den Befehl zur Installation der node_modules aus:
````bash
npm install
````
3. Führe den Befehl zur Kompilierung der Vue-Application auf:
````bash
npm run build
````
4. Führe die Befehle aus um die Dateien in den Webserver einzubetten:
````bash
sudo zip -r dist.zip /Bauinger-smartkeplermirror/frontend/smart-kepler/dist
sudo mv /Bauinger-smartkeplermirror/frontend/smart-kepler/dist.zip /var/www/html
sudo unzip /var/www/html
````
>Tipp: wenn zu wenig Speicherplatz auf dem Raspberry Pi frei ist lass npm run build auf einem anderen PC laufen und kopiere den Dist Ordner mit einer Cloud auf den Raspberry PI.

5. Wechsle wieder auf die Webseite:
> STRG + ALT + F1


