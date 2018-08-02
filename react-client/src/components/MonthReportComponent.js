import React, {Component} from 'react';

export class MonthReportComponent extends Component {

    render() {
        return (
            <div>
                {
                    this.props.month.total
                        ? <div>
                            <h2>Month</h2>
                            <p className="App-intro">Difference: {this.props.month.difference}</p>
                            <p className="App-intro">Total: {this.props.month.total}</p>
                            {this.props.month.days.map((function (day) {
                                return <p className="App-intro">{day.date} - {day.total}</p>
                            }))}
                          </div>
                        : <p className="App-intro">Loading...</p>
                }
            </div>
        );
    }

}