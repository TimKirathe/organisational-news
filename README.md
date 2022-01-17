# Project-Name

Organisational News


## Technologies Used

- Java, Postman and PostgresQL.


##### Setup Instructions and Installation

- Firstly, fork this project into your own remote repository.
- Run the command `git clone: https://github.com/<your-username>/organisational-news.git` in your terminal, to install it into your local repository.
- In your terminal run the command: `psql < create.sql` to setUp the necessary database needed to run App.java.
- Open the postgresql terminal and run the command: `CREATE DATABASE organisational_news_test WITH TEMPLATE organisational_news`. To create your test database.
- Change line 38 in your App.java file to: `String connectionString = "jdbc:postgresql://localhost:5432/organisational_news";`
- Add your postgresql data sources to connect to the two databases you have created locally.
- Your App is ready to run.


## Development

Want to contribute? Great!

To fix a bug or enhance an existing module, follow these steps:
- Fork the repo
- Create a new branch (git checkout -b improve-feature)
- Make the appropriate changes in the files
- Add changes to reflect the changes made
- Commit your changes (git commit -am 'Improve feature')
- Push to the branch (git push origin improve-feature)
- Create a Pull Request


## Known Bugs

If you find a bug (the website couldn't handle the query and or gave undesired results), kindly open an issue here by including your search query and the expected result.

If you'd like to request a new function, feel free to do so by opening an issue here. Please include sample queries and their corresponding results.


### License

*MIT*
Copyright (c) 2021 ***Timothy Kirathe***

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.