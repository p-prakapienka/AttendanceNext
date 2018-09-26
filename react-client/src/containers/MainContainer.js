import React, {Component} from 'react';
import {DayComponent} from "../components/DayComponent";
import {MonthReportComponent} from "../components/MonthReportComponent";

export class MainContainer extends Component {

    constructor(props) {
        super(props);

        this.state = {
            dayReport: {},
            monthReport: {}
        };

        this.interval = null;

        this.fetchDayReport = this.fetchDayReport.bind(this);
        this.fetchMonthReport = this.fetchMonthReport.bind(this);
    }

    fetchDayReport() {
        fetch('api/report/day', {
            credentials: "same-origin"
        }).then(response => {
            return response.json();
        }).then(data => {
            this.setState({dayReport : data})
        });
    }

    fetchMonthReport() {
        fetch('api/report/month', {
            credentials: 'same-origin'
        }).then(response => {
            return response.json();
        }).then(data => {
            this.setState({monthReport: data})
        });
    }

    componentDidMount() {
        this.fetchDayReport();
        this.fetchMonthReport();
        this.interval = setInterval(this.fetchDayReport, 60000);
    }

    componentWillMount() {
        clearInterval(this.interval);
    }

    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <h1 className="App-title">Attendance Next</h1>
                </header>
                <DayComponent day={this.state.dayReport} />
                <MonthReportComponent month={this.state.monthReport} />
            </div>
        );
    }

}