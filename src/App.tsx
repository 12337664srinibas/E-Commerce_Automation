import React, { useState } from 'react'
import Header from './components/Header'
import TestOverview from './components/TestOverview'
import TestExecution from './components/TestExecution'
import ProjectStructure from './components/ProjectStructure'
import ReportViewer from './components/ReportViewer'

function App() {
  const [activeTab, setActiveTab] = useState('overview')

  const tabs = [
    { id: 'overview', label: 'Test Overview', icon: '📊' },
    { id: 'execution', label: 'Test Execution', icon: '🚀' },
    { id: 'structure', label: 'Project Structure', icon: '🏗️' },
    { id: 'reports', label: 'Reports & Data', icon: '📋' }
  ]

  const renderActiveTab = () => {
    switch (activeTab) {
      case 'overview':
        return <TestOverview />
      case 'execution':
        return <TestExecution />
      case 'structure':
        return <ProjectStructure />
      case 'reports':
        return <ReportViewer />
      default:
        return <TestOverview />
    }
  }

  return (
    <div className="min-h-screen bg-gray-50">
      <Header />
      
      {/* Navigation Tabs */}
      <div className="bg-white border-b border-gray-200">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <nav className="flex space-x-8">
            {tabs.map((tab) => (
              <button
                key={tab.id}
                onClick={() => setActiveTab(tab.id)}
                className={`py-4 px-1 border-b-2 font-medium text-sm transition-colors duration-200 ${
                  activeTab === tab.id
                    ? 'border-aerospace-500 text-aerospace-600'
                    : 'border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300'
                }`}
              >
                <span className="mr-2">{tab.icon}</span>
                {tab.label}
              </button>
            ))}
          </nav>
        </div>
      </div>

      {/* Main Content */}
      <main className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        {renderActiveTab()}
      </main>
    </div>
  )
}

export default App