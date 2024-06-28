# LQSC - Local Quarkus Angular Client Konzept

## Browser Web-Anwendung

- Quarkus (Local-Quarkus-Server-Client oder LQSC) mit integriertem Angular Projekt
- Wird beim Systemstart ausgeführt
- Desktop Browser-Shortcut zu der lokalen, mit dem Quarkus-Client-Server betriebenen, Angular-Anwendung.

## LQSC (Local-Quarkus-Server-Client) Diagramm

![LQCS Diagram](https://github.com/tarekst/local-quarkus-angular-client-concept/blob/master/LQSC.png?raw=true)

## Executeable mit eigenem Fenster (Optional)

- Fundament ist die Browser Web-Anwendung
- Zusätzlich gibt es ein kleines NodeJS Electron Projekt, das ein Chromium based Fenster erstellt, welches mit dem electron-packager das Electron Projekt zu einer Windows Executeable Datei verpackt. 
- Durch Electron getriggerte Aktionen oder Events, wie z.B. Aktionen aus dem Electron Browser Menu, können an den Local-Quarkus-Server-Client (LQSC) gesendet werden, um Methoden in Quarkus zu triggern oder über Quarkus diese Aktionen/Events, durch die Quarkus WebSockets Extension, direkt interaktiv in die Angular Anwendung zu übertragen.



## Authors

- [@tarekst](https://github.com/tarekst)

