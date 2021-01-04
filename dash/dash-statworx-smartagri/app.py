import dash
import dash_html_components as html
import dash_core_components as dcc
import plotly.graph_objects as go
import pandas as pd
from dash.dependencies import Input, Output

# https://towardsdatascience.com/a-gentle-invitation-to-interactive-visualization-with-dash-a200427ccce9
# https://stackoverflow.com/questions/56215812/how-to-fix-callback-error-in-plotly-dash-for-a-dropdown-menu

# Load data
df = pd.read_csv('data/nafis_county_raw_details.csv', index_col=0, parse_dates=True)
df.index = pd.to_datetime(df['nafis_price_date'])

# Initialize the app
app = dash.Dash(__name__)
app.config.suppress_callback_exceptions = True


def get_options(list_commodity):
    dict_list = []
    for i in list_commodity:
        dict_list.append({'label': i, 'value': i})

    return dict_list


app.layout = html.Div(
    children=[
        html.Div(className='row',
                 children=[
                    html.Div(className='four columns div-user-controls',
                             children=[
                                 html.H2('DASH - CROP PRICES'),
                                 html.P('Visualising time series with Plotly - Dash.'),
                                 html.P('Pick one or more stocks from the dropdown below.'),
                                 html.Div(
                                     className='div-for-dropdown',
                                     children=[
                                         dcc.Dropdown(id='commodityselector', options=get_options(df['commodity'].unique()),
                                                      multi=True, 
													                           value=[df['commodity'].sort_values()[0]],
                                                      style={'backgroundColor': '#1E1E1E'},
                                                      className='commodityselector'
                                                      ),
                                     ],
                                     style={'color': '#1E1E1E'})
                                ]
                             ),
                    html.Div(className='eight columns div-for-charts bg-grey',
                             children=[
                                 dcc.Graph(id='timeseries', config={'displayModeBar': False}, animate=True)
                             ])
                              ])
        ]

)


# Callback for timeseries price
@app.callback(Output('timeseries', 'figure'),
              [Input('commodityselector', 'value')])
def update_graph(selected_dropdown_value):
    trace1 = []
    df_sub = df
    for commodity in selected_dropdown_value:
        trace1.append(go.Scatter(x=df_sub[df_sub['commodity'] == commodity].index,
                                 y=df_sub[df_sub['commodity'] == commodity]['value'],
                                 mode='lines',
                                 opacity=0.7,
                                 name=commodity,
                                 textposition='bottom center'))
    traces = [trace1]
    data = [val for sublist in traces for val in sublist]
    figure = {'data': data,
              'layout': go.Layout(
                  colorway=["#5E0DAC", '#FF4F00', '#375CB1', '#FF7400', '#FFF400', '#FF0056'],
                  template='plotly_dark',
                  paper_bgcolor='rgba(0, 0, 0, 0)',
                  plot_bgcolor='rgba(0, 0, 0, 0)',
                  margin={'b': 15},
                  hovermode='x',
                  autosize=True,
                  title={'text': 'Crop Prices', 'font': {'color': 'white'}, 'x': 0.5},
                  xaxis={'range': [df_sub.index.min(), df_sub.index.max()]},
              ),

              }

    return figure


if __name__ == '__main__':
    app.run_server(debug=True)
