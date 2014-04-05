
function graph(names, numbers) {
        var options = {
            chart: {
                renderTo: 'container',
				type: 'bar'
            },
            title: {
                text: 'Top 10 Nodes with Failures'
            },
            subtitle: {
                text: 'Given Time Period'
            },
            xAxis: {
                categories: names,
                title: {
                    text: 'Nodes'
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Failures',
                    align: 'high'
                },
                labels: {
                    overflow: 'justify'
                }
            },
            tooltip: {
                valueSuffix: ' failures'
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -40,
                y: 100,
                floating: true,
                borderWidth: 1,
                backgroundColor: '#FFFFFF',
                shadow: true,
				enabled: false
				
				
				
            },
			exporting: { enabled: false},
            credits: {
                enabled: false
            },
            series: [ {
                name: 'Failures',
                data: numbers
            }]
        };
		
		var chart = new Highcharts.Chart(options);
    }