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

**Step 1:** Logging into Heroku using heroku cli
```bash
jalaz@jalaz-personal:~/nodejs-heroku$ heroku login
heroku: Press any key to open up the browser to login or q to exit:
Opening browser to https://cli-auth.heroku.com/auth/cli/browser/xxxxx-xxx-xxxxxxx-xxx-xxxxx
Logging in... done
Logged in as jalazkumar1208@gmail.com
```

**Step 2:** Create a heroku app inside the directory
```bash
jalaz@jalaz-personal:~/nodejs-heroku$ heroku create nodejs-heroku-jalaz
Creating ⬢ nodejs-heroku-jalaz... done
https://nodejs-heroku-jalaz.herokuapp.com/ | https://git.heroku.com/nodejs-heroku-jalaz.git
```

**Step 3:** Set Buildpacks for the app
```bash
jalaz@jalaz-personal:~/nodejs-heroku$ heroku buildpacks:set heroku/nodejs --app nodejs-heroku-jalaz
Buildpack set. Next release on nodejs-heroku-jalaz will use heroku/nodejs.
Run git push heroku master to create a new release using this buildpack.
```

**Step 4:** Create a `Procfile` with the following content.
```text
web: node index.js
```

_Procfile: A text file in the root directory of your application, to explicitly declare what command should be executed to start your app_

**Step 5:** Commit these new deployment changes.
```bash
jalaz@jalaz-personal:~/nodejs-heroku$ git status
On branch master
Untracked files:
  (use "git add <file>..." to include in what will be committed)

	Procfile

nothing added to commit but untracked files present (use "git add" to track)
jalaz@jalaz-personal:~/nodejs-heroku$ git add Procfile
jalaz@jalaz-personal:~/nodejs-heroku$ git commit -m "Added Procfile for heroku deploy"
[master 7883275] Added Procfile for heroku deploy
 1 file changed, 1 insertion(+)
 create mode 100644 Procfile
```

**Step 6:** Link heroku app with heroku git repo
```bash
jalaz@jalaz-personal:~/nodejs-heroku$ git remote add heroku https://git.heroku.com/nodejs-heroku-jalaz.git
jalaz@jalaz-personal:~/nodejs-heroku$ git remote -v
heroku	https://git.heroku.com/nodejs-heroku-jalaz.git (fetch)
heroku	https://git.heroku.com/nodejs-heroku-jalaz.git (push)
```

**Step 7:** Push to deploy.
```bash
jalaz@jalaz-personal:~/nodejs-heroku$ git push heroku master
Enumerating objects: 9, done.
Counting objects: 100% (9/9), done.
Delta compression using up to 4 threads
Compressing objects: 100% (7/7), done.
Writing objects: 100% (9/9), 5.23 KiB | 1.74 MiB/s, done.
Total 9 (delta 1), reused 0 (delta 0)
remote: Compressing source files... done.
remote: Building source:
remote:
remote: -----> Node.js app detected
remote:        
remote: -----> Creating runtime environment
remote:        
remote:        NPM_CONFIG_LOGLEVEL=error
remote:        NODE_ENV=production
remote:        NODE_MODULES_CACHE=true
remote:        NODE_VERBOSE=false
remote:        
remote: -----> Installing binaries
remote:        engines.node (package.json):  unspecified
remote:        engines.npm (package.json):   unspecified (use default)
remote:        
remote:        Resolving node version 12.x...
remote:        Downloading and installing node 12.18.3
remote:        Using default npm version: 6.14.6
remote:        
remote: -----> Installing dependencies
remote:        Installing node modules (package.json + package-lock)
remote:        added 50 packages from 37 contributors and audited 50 packages in 1.732s
remote:        found 0 vulnerabilities
remote:        
remote:        
remote: -----> Build
remote:        
remote: -----> Caching build
remote:        - node_modules
remote:        
remote: -----> Pruning devDependencies
remote:        audited 50 packages in 0.68s
remote:        found 0 vulnerabilities
remote:        
remote:        
remote: -----> Build succeeded!
remote: -----> Discovering process types
remote:        Procfile declares types -> web
remote:
remote: -----> Compressing...
remote:        Done: 22.7M
remote: -----> Launching...
remote:        Released v3
remote:        https://nodejs-heroku-jalaz.herokuapp.com/ deployed to Heroku
remote:
remote: Verifying deploy... done.
To https://git.heroku.com/nodejs-heroku-jalaz.git
 * [new branch]      master -> master
```

_Get local copy of the project using `wget https://github.com/jaykay12/tech/assets/demos/nodejs-heroku.zip`_
