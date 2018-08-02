import React, {Component} from 'react';

export class DayComponent extends Component {

    render() {
        return (
            <div>
                <h2>Today</h2>
                <p className="App-intro">Total: {this.props.day.total}</p>
            </div>
        );
    }

}