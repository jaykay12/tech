---
layout: post
title: Deploying nodejs API on heroku
categories: [Deployment, Javascript, Heroku]
---

## Terminology

**nodeJS:** Node.js is an open-source, cross-platform, JavaScript runtime environment that executes JavaScript code outside a web browser. Built on Chrome's V8 JavaScript engine.

**npm:** npm is a package manager for the JavaScript programming language. It is the default package manager for the JavaScript runtime environment Node.js. It consists of a command line client, also called npm, and an online database of public and paid-for private packages, called the npm registry.

**expressJS:** Express.js, or simply Express, is a web application framework for Node.js. It is designed for building web applications and APIs. It has been called the de facto standard server framework for Node.js.

**Heroku:** Heroku is a cloud platform as a service supporting several programming languages.

> Check if nodeJS & NPM is installed in your workstation using,
> `node --version` & `npm --version`

## Creating a nodeJS API locally

**Step 1:** Initialise NPM & creation of the file: `package.json`

```bash
jalaz@jalaz-personal:~/nodejs-heroku$ npm init
This utility will walk you through creating a package.json file.
It only covers the most common items, and tries to guess sensible defaults.

See `npm help init` for definitive documentation on these fields
and exactly what they do.

Use `npm install <pkg>` afterwards to install a package and
save it as a dependency in the package.json file.

Press ^C at any time to quit.
package name: (nodejs-heroku)
version: (1.0.0)
description: App for demo blog post
entry point: (index.js)
test command:
git repository:
keywords:
author: Jalaz Kumar
license: (ISC)
About to write to /nodejs-heroku/package.json:

{
  "name": "nodejs-heroku",
  "version": "1.0.0",
  "description": "App for demo blog post",
  "main": "index.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1"
  },
  "author": "Jalaz Kumar",
  "license": "ISC"
}


Is this OK? (yes) yes
```

**Step 2:** Create a file `index.js` with the following content:

```javascript
const express = require('express')
const app = express()
const port = 9211

app.get('/', (req, res) => res.send('nodeJS API Running!'))

app.listen(port, () => console.log(`nodeJS app listening at http://localhost:${port}`))
```

**Step 3:** Install ExpressJS & install npm dependencies into `node_modules`.

```bash
jalaz@jalaz-personal:~/nodejs-heroku$ npm install express
npm notice created a lockfile as package-lock.json. You should commit this file.
npm WARN nodejs-heroku@1.0.0 No repository field.

+ express@4.17.1
added 50 packages from 37 contributors and audited 50 packages in 707.86s
found 0 vulnerabilities
```

**Step 4:** Run app locally
```bash
jalaz@jalaz-personal:~/nodejs-heroku$ node index.js
nodeJS app listening at http://localhost:9211
```

> Now hit http://localhost:9211/ from browser, if `nodeJS API Running!` is returned, then you got your nodeJS app running on local machine.

**Step 5:** In Step 4, You can alo edit `package.json` to have following entry in the key "scripts"
```json
"start": "node index.js",
```

& run the app locally using
```bash
jalaz@jalaz-personal:~/nodejs-heroku$ npm start

> nodejs-heroku@1.0.0 start /home/jalaz/Documents/Github/working-directory/nodejs-heroku
> node index.js

nodeJS app listening at http://localhost:9211
```

## Deploying API on Heroku
_<Coming soon>_
