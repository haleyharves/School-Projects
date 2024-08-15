from flask import Flask
from flask import render_template
from flask import Flask, render_template, request, redirect, url_for, flash
import pymysql


app = Flask(__name__)
app.secret_key = 'your_secret_key' 

@app.route('/')
def home():
    return render_template('Home.html')

def execute_query(query, args=None):
    host = "database-1.cric8i2c8imd.us-east-1.rds.amazonaws.com"
    user = "admin"
    password = "RDS!2004"
    db = "MyLetterboxd"
    
    conn = pymysql.connect(
        host=host,
        user=user,
        password=password,
        db=db,
    )

    cur = conn.cursor()
    cur.execute(query)
    rows = cur.fetchall()
    cur.close()
    conn.close() 
    return rows

def execute_queryInsert(query, args=None):
    # Replace these with your RDS instance details
    host = "database-1.cric8i2c8imd.us-east-1.rds.amazonaws.com"
    user = "admin"
    password = "RDS!2004"
    db = "MyLetterboxd"

    conn = pymysql.connect(
        host=host,
        user=user,
        password=password,
        db=db,
    )

    cur = conn.cursor()
    if args:
        cur.execute(query, args)
    else:
        cur.execute(query)
    conn.commit()  # Commit the transaction to make the changes persistent
    cur.close()
    conn.close()

def execute_insert_IFNOT(table, title):
    host = "database-1.cric8i2c8imd.us-east-1.rds.amazonaws.com"
    user = "admin"
    password = "RDS!2004"
    db = "MyLetterboxd"

    conn = pymysql.connect(
        host=host,
        user=user,
        password=password,
        db=db,
    )
    cur = conn.cursor()
    
    cur.execute("SELECT * FROM {} WHERE title = %s".format(table), (title,))
    existing_title = cur.fetchone()

    if existing_title:
        print("Title already exists in the database.")
    else:
   
        cur.execute("INSERT INTO {} (title) VALUES (%s)".format(table), (title,))
        conn.commit()  
        print("Title inserted successfully.")

    cur.close()
    conn.close()

def execute_delete(query, args=None):
    host = "database-1.cric8i2c8imd.us-east-1.rds.amazonaws.com"
    user = "admin"
    password = "RDS!2004"
    db = "MyLetterboxd"
    try:
        conn = pymysql.connect(
            host=host,
            user=user,
            password=password,
            db=db,
        )

        cur = conn.cursor()
        if args:
            cur.execute(query, args)
        else:
            cur.execute(query)
        conn.commit()  # Commit the transaction to make the changes persistent
        cur.close()
        conn.close()
    except Exception as e:
        print("Error executing delete query:", e)
def execute_queryUpdate(query, args=None):
    # Replace these with your RDS instance details
    host = "database-1.cric8i2c8imd.us-east-1.rds.amazonaws.com"
    user = "admin"
    password = "RDS!2004"
    db = "MyLetterboxd"

    try:
        conn = pymysql.connect(
            host=host,
            user=user,
            password=password,
            db=db,
        )

        cur = conn.cursor()
        if args:
            cur.execute(query, args)
        else:
            cur.execute(query)
        conn.commit()  # Commit the transaction to make the changes persistent
        cur.close()
        conn.close()
    except Exception as e:
        print("Error executing update query:", e)


@app.route('/add-review', methods=['GET', 'POST'])
def add_review():
    if request.method == 'POST':
     
        title = request.form['Title']
        DateWatched = request.form['DateWatched']
        MyRating = request.form['MyRating']
        FirstWatch = request.form['FirstWatch']
        
        try:
            execute_queryInsert('''INSERT INTO MyDiary (Title, DateWatched, MyRating, FirstWatch) VALUES (%s,%s,%s,%s)''', (title, DateWatched,MyRating, FirstWatch))
        
            execute_insert_IFNOT('MovieInfo', title)
            
            flash('Review added successfully!', 'success')
            return redirect(url_for('display_reviews'))
        except Exception as e:
            flash(f'Error adding review: {str(e)}', 'error')
            return redirect(url_for('add_review'))
    else:

        return render_template('add_review.html')

@app.route('/delete-review', methods=['GET', 'POST'])
def delete_review():
    if request.method == 'POST':
        ID = request.form['id']
        try:
            execute_delete('''DELETE FROM MyDiary WHERE EntryID = %s''', (ID))
            flash('Review deleted successfully!', 'danger')
            return redirect(url_for('display_reviews'))
        except Exception as e:
            flash(f'Error deleting review: {str(e)}', 'error')
            return redirect(url_for('delete_review'))
    else:
        # Fetch all reviews from MyDiary table
        reviews = execute_query('''SELECT EntryID, Title, DateWatched FROM MyDiary''')
        return render_template('delete_review.html', reviews=reviews)

@app.route('/update-review', methods=['GET', 'POST'])
def update_review():
    if request.method == 'POST':
        review_id = request.form['id']
        title = request.form['title']
        date_watched = request.form['date_watched']
        my_rating = request.form['my_rating']
        first_watch = request.form['first_watch']
        
        try:
            # Update the review in the database
            execute_queryUpdate('''UPDATE MyDiary SET Title = %s, DateWatched = %s, MyRating = %s, FirstWatch = %s WHERE EntryID = %s''', (title, date_watched, my_rating, first_watch, review_id))
            
            flash('Review updated successfully!', 'success')
            return redirect(url_for('display_reviews'))
        except Exception as e:
            flash(f'Error updating review: {str(e)}', 'error')
            return redirect(url_for('update_review'))
    else:
        # Fetch all reviews from MyDiary table
        reviews = execute_query('''SELECT EntryID, Title, DateWatched FROM MyDiary''')
        selected_review_id = request.args.get('id')
        if selected_review_id:
            # Fetch details of the selected review
            selected_review = execute_query('''SELECT * FROM MyDiary WHERE EntryID = %s''', (selected_review_id,))
            return render_template('update_review.html', reviews=reviews, selected_review=selected_review[0])
        else:
            return render_template('update_review.html', reviews=reviews, selected_review=None)
            
@app.route('/update-movie', methods=['GET', 'POST'])
def update_movie():
    if request.method == 'POST':
        movie_id = request.form['id']
        title = request.form['title']
        director = request.form['Director']
        releaseYear = request.form['ReleaseYear']
        globalRating = request.form['GlobalRating']
        
        try:
            # Update the review in the database
            execute_queryUpdate('''UPDATE MovieInfo SET Title = %s, Director = %s, YearReleased = %s, GlobalRating = %s WHERE ID = %s''', (title, director, releaseYear, globalRating, movie_id))
            
            flash('Movie updated successfully!', 'success')
            return redirect(url_for('display_movies'))
        except Exception as e:
            flash(f'Error updating movie: {str(e)}', 'error')
            return redirect(url_for('update_movie'))
    else:
        movies = execute_query('''SELECT * FROM MovieInfo''')
        return render_template('update_movie.html', movies=movies)

@app.route('/display-reviews')
def display_reviews():
    users_list = execute_query('''SELECT *
                                    FROM MyDiary;''')
    return render_template('display_reviews.html', movies = users_list)

@app.route('/display-movies')
def display_movies():
    users_list = execute_query('''SELECT *
                                    FROM MovieInfo;''')
    return render_template('display_movies.html', movies = users_list)

@app.route('/display-all')
def display_all():
    aList = execute_query('''SELECT *
                            FROM MovieInfo
                            INNER JOIN MyDiary
                            ON MovieInfo.Title = MyDiary.Title''')
    return render_template('display_all.html', movies = aList)

    
if __name__ == '__main__':
    app.run(host='0.0.0.0', port = 8080, debug = True)
    


