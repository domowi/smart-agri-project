# Run this app with `python app.py` and
# visit http://127.0.0.1:8050/ in your web browser.

import dash

from layouts import layout_ga

# https://www.pythonistaplanet.com/python-flask-project-ideas/ - 7 Flask Project Ideas

external_stylesheets = ['https://codepen.io/chriddyp/pen/bWLwgP.css']

app = dash.Dash(__name__, external_stylesheets=external_stylesheets, url_base_pathname='/cc-nafis-price-report/prices-search/')

app.renderer = 'var renderer = new DashRenderer();'
app.config.suppress_callback_exceptions = True

if __name__ == '__main__':
    app.run_server(debug=True)