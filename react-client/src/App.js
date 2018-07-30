import React, {Component} from 'react';
import './App.css';

class App extends Component {

    constructor() {
        super();

        this.state = {
            dayReport: {}
        };

        this.fetchDayReport = this.fetchDayReport.bind(this);
    }

    fetchDayReport() {
        fetch('/api/report/day', {
            credentials: "same-origin"
        }).then(response => {
            return response.json();
        }).then(data => {
            this.setState({dayReport : data})
        });
    }

    componentDidMount() {
        this.fetchDayReport();
    }

    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <h1 className="App-title">Attendance Next</h1>
                </header>
                <p className="App-intro">
                    Today: {this.state.dayReport.total}
                </p>
            </div>
        );
    }
}

export default App;
