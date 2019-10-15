# 482f19-jajeimo
This is the repository for team jajeimo to develop their Auction application in Fall 2019 Software Engineering course.

# Team Members
1. Javon Kitson (ja)
2. Jennifer Moutenot (je)
3. Ian Leppo (i)
4. Mollie Morrow (mo)

# Team Lead
Jennifer Moutenot


# Greyhound Auctions

## Classes
1. User
- String name
- String email
- String password
- Boolean signedIn
- List<Items> itemsBidOn
- List<Items> itemsCurrentHighestBidderOn
- bid()
- Auto-bid()
2. Admin?
3. Item
- String title
- String description
- List<String> tags
- Double currentHighestBid
- String currentHighestBidder
4. AuctionItems
- List<Item> items
- sortByTag(String tag)
- sortByTitle(String title)
