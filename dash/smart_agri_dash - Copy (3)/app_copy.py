# Run this app with `python app.py` and
# visit http://127.0.0.1:8050/ in your web browser.

import dash
import dash_table

import dash_core_components as dcc
import dash_html_components as html
from dash.dependencies import Input, Output

import pandas as pd
import io
import xlsxwriter
from flask import send_file

#from layouts import layout_ga_category, noPage
import callbacks

external_stylesheets = ['https://codepen.io/chriddyp/pen/bWLwgP.css']

app = dash.Dash(__name__, external_stylesheets=external_stylesheets, url_base_pathname='/cc-travel-report/paid-search/')

app.renderer = 'var renderer = new DashRenderer();'
app.config.suppress_callback_exceptions = True

# see https://dash.plot.ly/external-resources to alter header, footer and favicon
app.index_string = ''' 
<!DOCTYPE html>
<html>
    <head>
        {%metas%}
        <title>County Agricultural Commodities Price Report</title>
        {%favicon%}
        {%css%}
    </head>
    <body>
        {%app_entry%}
        <footer>
            {%config%}
            {%scripts%}
        </footer>
        <div>County Agricultural Commodities Price Report</div>
    </body>
</html>
'''

app.layout = html.Div([
    dcc.Location(id='url', refresh=False),
    html.Div(id='page-content')
])

# Update page
# # # # # # # # #
@app.callback(Output('page-content', 'children'),
              [Input('url', 'pathname')])
def display_page(pathname):
    if pathname == '/cc-travel-report/overview-ga/':
        return layout_ga_category
    elif pathname == '/cc-travel-report/paid-search/':
        return layout_ga_category
    else:
        return noPage

if __name__ == '__main__':
    app.run_server(debug=True)