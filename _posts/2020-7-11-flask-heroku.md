---
layout: post
title: Deploying python-flask API on heroku
categories: [Deployment, Python, Heroku]
---

## Terminology
 - **virtualenv:** A tool to create isolated Python environments
 - **flask:** A micro web framework written in Python. It is classified as a microframework because it does not require particular tools or libraries.

 It has no database abstraction layer, form validation, or any other components where pre-existing third-party libraries provide common functions.
 - **heroku:** A cloud platform as a service supporting several programming languages.

## Creating a flask API locally

**Step 1:** Initialise virtualenv for the flask API
```bash
jalaz@jalaz-personal:~/tech/assets/demos/flask-heroku$ virtualenv venv
Using base prefix '/usr'
New python executable in /tech/assets/demos/flask-heroku/venv/bin/python3
Not overwriting existing python script /tech/assets/demos/flask-heroku/venv/bin/python (you must use /tech/assets/demos/flask-heroku/venv/bin/python3)
Installing setuptools, pip, wheel...
done.
```

**Step 3:** Activate the virtualenv & install flask Using
```bash
jalaz@jalaz-personal:~/tech/assets/demos/flask-heroku$ source venv/bin/activate
(venv) jalaz@jalaz-personal:~/tech/assets/demos/flask-heroku$ pip install flask
Collecting flask
  Using cached Flask-1.1.2-py2.py3-none-any.whl (94 kB)
Collecting Jinja2>=2.10.1
  Using cached Jinja2-2.11.2-py2.py3-none-any.whl (125 kB)
Collecting itsdangerous>=0.24
  Using cached itsdangerous-1.1.0-py2.py3-none-any.whl (16 kB)
Collecting Werkzeug>=0.15
  Using cached Werkzeug-1.0.1-py2.py3-none-any.whl (298 kB)
Collecting click>=5.1
  Downloading click-7.1.2-py2.py3-none-any.whl (82 kB)
     |████████████████████████████████| 82 kB 16 kB/s
Collecting MarkupSafe>=0.23
  Using cached MarkupSafe-1.1.1-cp37-cp37m-manylinux1_x86_64.whl (27 kB)
Installing collected packages: MarkupSafe, Jinja2, itsdangerous, Werkzeug, click, flask
Successfully installed Jinja2-2.11.2 MarkupSafe-1.1.1 Werkzeug-1.0.1 click-7.1.2 flask-1.1.2 itsdangerous-1.1.0
```

**Step 3:** Create a `main.py` file with the following content

```python
from flask import Flask
app = Flask(__name__)


@app.route('/')
def intro():
    return "Flask App Running...!"

if __name__ == '__main__':
    app.run()
```

**Step 4:** Run the flask app using `python app.py`
```bash
(venv) jalaz@jalaz-personal:~/tech/assets/demos/flask-heroku$ python app.py
 * Serving Flask app "app" (lazy loading)
 * Environment: production
   WARNING: This is a development server. Do not use it in a production deployment.
   Use a production WSGI server instead.
 * Debug mode: off
 * Running on http://127.0.0.1:5000/ (Press CTRL+C to quit)
```

**Step 5:** On hitting, `localhost:5000`, if you get _Flask App Running...!_. Kudos, You got your flask API running locally.

## Using already created flask API
**Step 1:**
Get local copy of the project using
```bash
wget https://github.com/jaykay12/tech/assets/demos/flask-heroku.zip
```

**Step 2:** Create a virtualenv for the python web app using
```bash
virtualenv venv
```

**Step 3:** Activate the virtualenv using
```bash
source venv/bin/activate
```

**Step 4:** Install all the packages using
```bash
pip install -r requirements.txt
```

**Step 5:** Run the flask app using `python app.py`
```bash
(venv) jalaz@jalaz-personal:~/tech/assets/demos/flask-heroku$ python app.py
 * Serving Flask app "app" (lazy loading)
 * Environment: production
   WARNING: This is a development server. Do not use it in a production deployment.
   Use a production WSGI server instead.
 * Debug mode: off
 * Running on http://127.0.0.1:5000/ (Press CTRL+C to quit)
```

**Step 5:** On hitting, `localhost:5000`, if you get _Flask App Running...!_. Kudos, You got your flask API running locally.

## Deploying on Heroku

_<--Coming soon-->_
