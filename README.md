Welcome to HashMap Heroes, the project where we wrangle data, battle bias, and laugh in the face of complexity—all while trying not to cry over a few lines of Java code. This was my final project for COMP 250, and it’s proof that you can indeed turn a dry, academic assignment into something that’s actually kind of cool (and maybe even useful).

"What’s the Deal?"
In HashMap Heroes, I took on the noble quest of building a hash table from scratch—because who needs HashMap when you can reinvent the wheel, right? After I’d conquered that mountain, I dove into the murky waters of data analysis using reviews from RateMyProfessors.com. The mission? To see if students are sneakily biased when they rate their professors. Spoiler alert: They totally are.

The Adventure Begins
Part 1: The Hash Table—A Love-Hate Relationship
Custom Hashing: Forget boring old HashMap; I crafted my own. My hash table does all the usual stuff—put, get, remove, and even rehash when things get a little too cozy. Think of it like building IKEA furniture, but with fewer missing screws and more data integrity.
Buckets and Pairs: I stored data in buckets (which are really just LinkedLists in disguise), and each bucket holds MyPair objects (fancy speak for "key-value pairs"). It’s like organizing your life into tiny, well-labeled boxes—if only life were that simple.
Part 2: Data Analysis—Making Sense of Student Rants
Once the hash table was ready to roll, I turned my attention to analyzing student reviews. Armed with data from RateMyProfessors.com, I uncovered trends, biases, and probably some deeply buried student frustrations. Here’s the breakdown:

- Rating Distribution by Professor: Want to know how students really feel about Professor X? This feature maps ratings (from 1 to 5) to the number of reviews they’ve received. It’s like reading Yelp reviews, but with more academic whining.

- Rating Count Per Professor by School: Curious about which professor is the big cheese at your school? This tool shows you the average rating for each professor and how many times students have griped about them. Spoiler: Everyone loves an easy A.

- Gender by Keyword: Ever wondered if students describe male and female professors differently? Pop in a keyword and see how often it’s used for each gender. Because nothing says "equality" like checking if “caring” is code for “female professor.”

- Rating by Keyword: Is "amazing" reserved for the best, or does "fun" just mean "easy A"? Enter a keyword and find out how it correlates with different ratings. Perfect for those times when you want to prove that “cool” professors get better reviews.

- Rating by Gender: Let’s get serious—kind of. This feature lets you compare how students rate male vs. female professors, either in terms of quality or difficulty. Enter a gender and rating type, and watch the biases roll in.

"Why Should You Care?"
HashMap Heroes isn’t just about nerding out over data structures (though there’s plenty of that). It’s about shining a light on the subtle biases that shape our world—one student rant at a time. Plus, if you survive building a hash table from scratch, you can pretty much survive anything.

How to Get in on the Action
Clone the Repo: Grab the code and open it in your favorite Java IDE.
JavaFX Fun Time: Make sure you’ve got JavaFX set up so you can actually see the cool charts and graphs (they’re worth it, trust me).
Analyze Like a Pro: Use the tools to dig into the data and uncover the hidden truths behind those professor ratings.
