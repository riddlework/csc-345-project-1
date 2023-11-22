import pandas as pd
import matplotlib.pyplot as plt
csv_file = open("results.csv")
csv = csv_file.readlines()[1::]
print(csv)
csv = list(map(lambda x : x.strip().split(","), csv))

df = pd.DataFrame(csv, columns=["Algorithm", "Time", "Size"])

df["Time"] = df["Time"].astype(int)
df["Size"] = df["Size"].astype(int)
df = df.sort_values(by="Size")
df = df[df["Algorithm"] != "Genetic Algorithm"]

print(df.head)

# Plot the data
plt.figure(figsize=(10, 6))
for algorithm in df["Algorithm"].unique():
    subset = df[df["Algorithm"] == algorithm]
    plt.plot(subset["Size"], subset["Time"], label=algorithm)

plt.xlabel('Size')
plt.ylabel('Time')
plt.legend()
plt.show()