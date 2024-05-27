INSERT INTO
    financial_instruments (isin, name, ticker, description)
VALUES
    (
        'US0378331005',
        'Apple Inc.',
        'AAPL',
        'Apple Inc. is an American multinational technology company that specializes in designing, manufacturing, and marketing consumer electronics, software, and online services. Known for its innovative products such as the iPhone, iPad, and Mac, Apple has a significant influence on the technology industry. The company also operates a variety of services including the App Store, Apple Music, and iCloud.'
    ),
    (
        'US0231351067',
        'Amazon.com, Inc.',
        'AMZN',
        'Amazon.com, Inc. is a global e-commerce giant that also focuses on cloud computing, digital streaming, and artificial intelligence. Known for its disruptive innovation in various sectors, Amazon operates Amazon Web Services (AWS), which is a major cloud computing platform. The company continues to expand into new markets and technologies.'
    ),
    (
        'US5949181045',
        'Microsoft Corporation',
        'MSFT',
        'Microsoft Corporation is a leading technology company that develops, licenses, and supports a wide range of software products and services. Known for its Windows operating system and Office suite, Microsoft also excels in cloud computing through its Azure platform. The company is also involved in gaming, hardware, and digital services.'
    ),
    (
        'US30303M1027',
        'Meta Platforms, Inc.',
        'META',
        'Meta Platforms, Inc., formerly known as Facebook, Inc., is a major player in social media and technology. The company owns Facebook, Instagram, WhatsApp, and Oculus, focusing on connecting people and virtual reality. Meta is also investing heavily in developing the metaverse, a virtual shared space.'
    ),
    (
        'US02079K1079',
        'Alphabet Inc.',
        'GOOGL',
        'Alphabet Inc. is the parent company of Google, specializing in internet services and products. Known for its search engine, Alphabet also leads in online advertising, cloud computing, and software. The company is committed to innovation and has various subsidiaries in health tech, self-driving cars, and other sectors.'
    ),
    (
        'US7427181091',
        'The Procter & Gamble Company',
        'PG',
        'The Procter & Gamble Company is a leading consumer goods corporation that produces a wide range of hygiene and health products. Known for brands like Pampers, Tide, and Gillette, P&G operates globally with a focus on quality and innovation. The company continuously invests in research and development to stay competitive.'
    ),
    (
        'US7427181092',
        'Johnson & Johnson',
        'JNJ',
        'Johnson & Johnson is a multinational corporation that develops medical devices, pharmaceuticals, and consumer packaged goods. Known for its commitment to health and well-being, the company produces popular products like Tylenol and Band-Aid. J&J is also at the forefront of medical research and innovation.'
    ),
    (
        'US1912161008',
        'The Coca-Cola Company',
        'KO',
        'The Coca-Cola Company is a global beverage leader known for its flagship product, Coca-Cola. The company offers over 500 brands in more than 200 countries, including water, juices, and energy drinks. Coca-Cola focuses on sustainability, innovation, and expanding its product portfolio to meet consumer preferences.'
    ),
    (
        'US5949181046',
        'PepsiCo, Inc.',
        'PEP',
        'PepsiCo, Inc. is a multinational food and beverage corporation known for its diverse product range, including Pepsi, Mountain Dew, and Lay''s. The company emphasizes sustainable growth and innovation across its brands. PepsiCo operates globally, focusing on providing convenient and nutritious products.'
    ),
    (
        'US4581401001',
        'Intel Corporation',
        'INTC',
        'Intel Corporation is a leading technology company that designs and manufactures semiconductor chips. Known for its processors used in computers and data centers, Intel is at the forefront of technological advancements. The company invests heavily in research and development to drive innovation in computing and communication.'
    ),
    (
        'US4370761029',
        'The Home Depot, Inc.',
        'HD',
        'The Home Depot, Inc. is the largest home improvement retailer in the United States, offering a wide range of products and services. Known for its DIY and professional customer base, the company provides building materials, appliances, and tools. Home Depot focuses on customer service, innovation, and sustainability.'
    ),
    (
        'US1912161007',
        'Walmart Inc.',
        'WMT',
        'Walmart Inc. is a multinational retail corporation operating a chain of hypermarkets, discount department stores, and grocery stores. Known for its extensive product range and low prices, Walmart is a leader in the retail sector. The company emphasizes e-commerce, sustainability, and providing value to customers globally.'
    );

-- Insert users with explicit IDs
INSERT INTO users (id, username, name, surname, email)
VALUES
    (1, 'jdoe', 'John', 'Doe', 'jdoe@example.com'),
    (2, 'asmith', 'Alice', 'Smith', 'asmith@example.com'),
    (3, 'bwhite', 'Bob', 'White', 'bwhite@example.com'),
    (4, 'cjohnson', 'Carol', 'Johnson', 'cjohnson@example.com'),
    (5, 'dmiller', 'David', 'Miller', 'dmiller@example.com'),
    (6, 'ewilliams', 'Emma', 'Williams', 'ewilliams@example.com');


-- Insert portfolios with explicit IDs
INSERT INTO portfolios (portfolio_id, name, user_id)
VALUES
    (1, 'Technology Growth', 1),
    (2, 'Dividend Income', 1),
    (3, 'Healthcare Innovators', 2),
    (4, 'Low Volatility', 2),
    (5, 'Energy Sector', 3),
    (6, 'Emerging Markets', 3),
    (7, 'Biotech Growth', 4),
    (8, 'Sustainable Investments', 4),
    (9, 'High Yield Bonds', 5),
    (10, 'Real Estate Investment Trusts', 5),
    (11, 'Consumer Goods', 6),
    (12, 'Small Cap Growth', 6);

-- Insert positions for each portfolio using the initial set of tickers
INSERT INTO positions (portfolio_id, quantity, financial_instrument_id, purchase_price)
VALUES
    (1, 50, (SELECT id FROM financial_instruments WHERE ticker = 'AAPL'), 150.00),
    (1, 30, (SELECT id FROM financial_instruments WHERE ticker = 'MSFT'), 250.00),
    
    (2, 100, (SELECT id FROM financial_instruments WHERE ticker = 'KO'), 55.00),
    (2, 75, (SELECT id FROM financial_instruments WHERE ticker = 'PG'), 145.00),
    
    (3, 40, (SELECT id FROM financial_instruments WHERE ticker = 'JNJ'), 165.00),
    (3, 60, (SELECT id FROM financial_instruments WHERE ticker = 'PG'), 145.00),
    
    (4, 80, (SELECT id FROM financial_instruments WHERE ticker = 'KO'), 55.00),
    (4, 50, (SELECT id FROM financial_instruments WHERE ticker = 'JNJ'), 165.00),
    
    (5, 70, (SELECT id FROM financial_instruments WHERE ticker = 'INTC'), 60.00),
    (5, 80, (SELECT id FROM financial_instruments WHERE ticker = 'AMZN'), 105.00),
    
    (6, 100, (SELECT id FROM financial_instruments WHERE ticker = 'META'), 220.00),
    (6, 120, (SELECT id FROM financial_instruments WHERE ticker = 'GOOGL'), 70.00),
    
    (7, 60, (SELECT id FROM financial_instruments WHERE ticker = 'JNJ'), 230.00),
    (7, 70, (SELECT id FROM financial_instruments WHERE ticker = 'KO'), 67.00),
    
    (8, 90, (SELECT id FROM financial_instruments WHERE ticker = 'PG'), 700.00),
    (8, 100, (SELECT id FROM financial_instruments WHERE ticker = 'WMT'), 45.00),
    
    (9, 150, (SELECT id FROM financial_instruments WHERE ticker = 'KO'), 107.00),
    (9, 100, (SELECT id FROM financial_instruments WHERE ticker = 'PEP'), 88.00),
    
    (10, 200, (SELECT id FROM financial_instruments WHERE ticker = 'HD'), 90.00),
    (10, 150, (SELECT id FROM financial_instruments WHERE ticker = 'INTC'), 120.00),
    
    (11, 100, (SELECT id FROM financial_instruments WHERE ticker = 'PG'), 145.00),
    (11, 110, (SELECT id FROM financial_instruments WHERE ticker = 'PEP'), 60.00),
    
    (12, 200, (SELECT id FROM financial_instruments WHERE ticker = 'INTC'), 210.00),
    (12, 150, (SELECT id FROM financial_instruments WHERE ticker = 'AAPL'), 190.00);
